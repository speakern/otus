package ru.otus;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.User;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.mapper.JdbcMapper;
import ru.otus.jdbc.mapper.JdbcMapperImpl;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
/**
Самодельный ORM
        Цель: Научиться работать с jdbc.
        На практике освоить многоуровневую архитектуру приложения.
        Работа должна использовать базу данных H2.

        Создайте в базе таблицу User с полями:

        • id bigint(20) NOT NULL auto_increment
        • name varchar(255)
        • age int(3)

        Создайте свою аннотацию @Id

Создайте класс User (с полями, которые соответствуют таблице, поле id отметьте аннотацией).

        Реализуйте интерфейс JdbcMapper<T>, который умеет работать с классами, в которых есть поле с аннотацией @Id.
        JdbcMapper<T> должен сохранять объект в базу и читать объект из базы.
        Для этого надо реализовать оставшиеся интерфейсы из пакета mapper.
        Таким обзазом, получится надстройка над DbExecutor<T>, которая по заданному классу умеет генерировать sql-запросы.
        А DbExecutor<T> должен выполнять сгенерированные запросы.

        Имя таблицы должно соответствовать имени класса, а поля класса - это колонки в таблице.

        Проверьте его работу на классе User.

        За основу возьмите класс HomeWork.

        Создайте еще одну таблицу Account:
        • no bigint(20) NOT NULL auto_increment
        • type varchar(255)
        • rest number

        Создайте для этой таблицы класс Account и проверьте работу JdbcMapper на этом классе.
        Критерии оценки: Система оценки максимально соответсвует привычной школьной:
        3 и больше - задание принято (удовлетворительно).
        ниже - задание возвращается на доработку.
**/

public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
// Общая часть
        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);

// Работа с пользователем
        DbExecutorImpl<User> dbExecutor = new DbExecutorImpl<>();
        JdbcMapper<User> jdbcMapperUser = new JdbcMapperImpl<>(sessionManager, dbExecutor, User.class);
       // UserDao userDao = new UserDaoJdbcMapper(jdbcMapperUser);

// Код дальше должен остаться, т.е. userDao должен использоваться
        //var dbServiceUser = new DbServiceUserImpl(userDao);
        //var id = dbServiceUser.saveUser(new User(0, "dbServiceUser"));
        //Optional<User> user = dbServiceUser.getUser(id);

        //user.ifPresentOrElse(
        //        crUser -> logger.info("created user, name:{}", crUser.getName()),
        //        () -> logger.info("user was not created")
        //);
// Работа со счетом


    }

    private static void flywayMigrations(DataSource dataSource) {
        logger.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        logger.info("db migration finished.");
        logger.info("***");
    }
}
