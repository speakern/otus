package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDaoException;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcMapperImpl<T> implements JdbcMapper<T>{
    private static final Logger logger = LoggerFactory.getLogger(JdbcMapperImpl.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<T> dbExecutor;
    private final EntityClassMetaData<T> entityClassMetaData;
    private final EntitySQLMetaData entitySQLMetaData;

    private final String selectAllSqlQuery;
    private final String selectByIdSqlQuety;
    private final String insertSqlQuery;
    private final String updateSqlQuery;


    public JdbcMapperImpl(SessionManagerJdbc sessionManager, DbExecutor<T> dbExecutor, Class<T> tClass) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.entityClassMetaData = new EntityClassMetaDataImpl<>(tClass);
        this.entitySQLMetaData = new EntitySQLMetaDataImpl(this.entityClassMetaData);

        this.selectAllSqlQuery = this.entitySQLMetaData.getSelectAllSql();
        this.selectByIdSqlQuety = this.entitySQLMetaData.getSelectByIdSql();
        this.insertSqlQuery = this.entitySQLMetaData.getInsertSql();
        this.updateSqlQuery = this.entitySQLMetaData.getUpdateSql();

//        System.out.println("Все поля:");
//        entityClassMetaData.getAllFields().forEach(System.out::println);
//        System.out.println("Конструктор:");
//        System.out.println(entityClassMetaData.getConstructor());
//        System.out.println("Получить поле с аннотацией ID");
//        System.out.println(entityClassMetaData.getIdField());
//        System.out.println("Все поля без ID:");
//        entityClassMetaData.getFieldsWithoutId().forEach(System.out::println);
//        System.out.println("Получить имя");
//        System.out.println(entityClassMetaData.getName());
//        System.out.println("---------------------------------------------------");
//        System.out.println("Получить select всех элементов");
//        System.out.println(selectAllSqlQuery);
//        System.out.println("Получить select где ID = ");
//        System.out.println(selectByIdSqlQuety);
//        System.out.println("Получить insert");
//        System.out.println(insertSqlQuery);
//        System.out.println("Получить update");
//        System.out.println(updateSqlQuery);

    }

    @Override
    public long insert(T objectData) {
        try {
            return dbExecutor.executeInsert(getConnection(), insertSqlQuery,
                    Collections.singletonList(user.getName()));
//            return jdbcMapper.insert(user);
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
        return 0;
    }

    @Override
    public void update(T objectData) {

    }

    @Override
    public void insertOrUpdate(T objectData) {

    }

    @Override
    public T findById(Object id, Class<T> clazz) {
        Map<String, Object> queryValues = new HashMap<>();
        try {
            dbExecutor.executeSelect(getConnection(), selectByIdSqlQuety,
                    id, rs -> {
                        try {
                            if (rs.next()) {
                                List<Field> fieldList = entityClassMetaData.getFieldsWithoutId();
                                //return new User(rs.getLong("id"), rs.getString("name"));
                                for (Field field: fieldList) {
                                    queryValues.put(field.getName(), rs.getObject(field.getName()));
                                }
                                return null;
                            }
                        } catch (SQLException e) {
                            logger.error(e.getMessage(), e);
                        }
                        return null;
                    });
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

    private T createObject(Class<T> clazz, Map<String, Object> queryValues ) {
        Constructor<T> constructor = entityClassMetaData.getConstructor();
        T object = null;
        try {
            int countPatameters = queryValues.size();
            Object[] objects= new Object[countPatameters];
            Parameter[] parameters = constructor.getParameters();
            for (int i = 0; i < countPatameters; i++) {
                System.out.println(parameters[i].getName());
            }
            object = constructor.newInstance(objects);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
