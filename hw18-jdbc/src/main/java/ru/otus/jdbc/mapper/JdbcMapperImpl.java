package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDaoException;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JdbcMapperImpl<T> implements JdbcMapper<T> {
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

        logger.info("Все поля:");
        entityClassMetaData.getAllFields().forEach(e -> logger.info(e.toString()));
        logger.info("Конструктор:");
        logger.info(entityClassMetaData.getConstructor().toString());
        logger.info("Получить поле с аннотацией ID");
        logger.info(entityClassMetaData.getIdField().toString());
        logger.info("Все поля без ID:");
        entityClassMetaData.getFieldsWithoutId().forEach(e -> logger.info(e.toString()));
        logger.info("Получить имя");
        logger.info(entityClassMetaData.getName());
        logger.info("---------------------------------------------------");
        logger.info("Получить select всех элементов");
        logger.info(selectAllSqlQuery);
        logger.info("Получить select где ID = ");
        logger.info(selectByIdSqlQuety);
        logger.info("Получить insert");
        logger.info(insertSqlQuery);
        logger.info("Получить update");
        logger.info(updateSqlQuery);

    }

    @Override
    public long insert(T objectData) {
        try {
            List<Object> params = entityClassMetaData.getFieldsWithoutId().stream()
                    .map((e) -> getFieldValue(objectData, e.getName()))
                    .collect(Collectors.toList());
            long result = dbExecutor.executeInsert(getConnection(), insertSqlQuery, params);
            return result;
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
    }

    @Override
    public void update(T objectData) {

    }

    @Override
    public void insertOrUpdate(T objectData) {

    }

    @Override
    public T findById(Object id, Class<T> clazz) {
        Optional<T> optionalT = Optional.empty();
        try {
            optionalT = dbExecutor.executeSelect(getConnection(), selectByIdSqlQuety,
                    id, rs -> {
                        try {
                            if (rs.next()) {
                                List<Field> fieldList = entityClassMetaData.getAllFields();
                                List<Object> listObjects = new ArrayList<>();
                                for (Field field : fieldList) {
                                    listObjects.add(rs.getObject(field.getName()));
                                }
                                return createObject(listObjects);
                            }
                        } catch (SQLException e) {
                            logger.error(e.getMessage(), e);
                        }
                        return null;
                    });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (!optionalT.isEmpty()) return optionalT.get();
        return null;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

    private T createObject(List<Object> listObjects) {
        Constructor<T> constructor = entityClassMetaData.getConstructor();
        T object = null;
        try {
            object = constructor.newInstance(listObjects.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public Object getFieldValue(Object object, String name) {
        try {
            var field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
