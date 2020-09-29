package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.Connection;

public class JdbcMapperImpl<T> implements JdbcMapper<T>{
    private static final Logger logger = LoggerFactory.getLogger(JdbcMapperImpl.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<T> dbExecutor;
    private final EntityClassMetaData<T> entityClassMetaData;
    private final EntitySQLMetaData entitySQLMetaData;

    public JdbcMapperImpl(SessionManagerJdbc sessionManager, DbExecutor<T> dbExecutor, Class<T> tClass) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.entityClassMetaData = new EntityClassMetaDataImpl<>(tClass);
        this.entitySQLMetaData = new EntitySQLMetaDataImpl(this.entityClassMetaData);

        System.out.println("Все поля:");
        entityClassMetaData.getAllFields().forEach(System.out::println);
        System.out.println("Конструктор:");
        System.out.println(entityClassMetaData.getConstructor());
//        System.out.println("Все поля без ID:");
//        entityClassMetaData.getFieldsWithoutId().forEach(System.out::println);
        System.out.println("Получить поле с аннотацией ID");
        System.out.println(entityClassMetaData.getIdField());
        System.out.println("Все поля без ID:");
        entityClassMetaData.getFieldsWithoutId().forEach(System.out::println);
        System.out.println("Получить имя");
        System.out.println(entityClassMetaData.getName());
        System.out.println("---------------------------------------------------");
        System.out.println("Получить select всех элементов");
        System.out.println(entitySQLMetaData.getSelectAllSql());
        System.out.println("Получить select где ID = ");
        System.out.println(entitySQLMetaData.getSelectByIdSql());
        System.out.println("Получить insert");
        System.out.println(entitySQLMetaData.getInsertSql());

    }

    @Override
    public long insert(T objectData) {
//        try {
////            return dbExecutor.executeInsert(getConnection(), "insert into user(name) values (?)",
////                    Collections.singletonList(user.getName()));
//            return jdbcMapper.insert(user);
//        } catch (Exception e) {
//            throw new UserDaoException(e);
//        }
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
        try {
//            return dbExecutor.executeSelect(getConnection(), "select id, name from user where id  = ?",
//                    id, rs -> {
//                        try {
//                            if (rs.next()) {
//                                return new User(rs.getLong("id"), rs.getString("name"));
//                            }
//                        } catch (SQLException e) {
//                            logger.error(e.getMessage(), e);
//                        }
//                        return null;
//                    });
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
}
