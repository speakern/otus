package ru.otus;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.domain.AddressDataSet;
import ru.otus.domain.PhoneDataSet;
import ru.otus.domain.User;
import ru.otus.flyway.MigrationsExecutor;
import ru.otus.flyway.MigrationsExecutorFlyway;
import ru.otus.utils.HibernateUtils;

@Configuration
public class DBConfig {
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    @Bean
    public SessionFactory sessionFactory() {
        SessionFactory sessionFactory =
                HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE, User.class, PhoneDataSet.class, AddressDataSet.class);
        return sessionFactory;
    }

    @Bean
    public MigrationsExecutor migrationsExecutor() {
        MigrationsExecutor migrationsExecutor = new MigrationsExecutorFlyway(HIBERNATE_CFG_FILE);
        return migrationsExecutor;
    }
}
