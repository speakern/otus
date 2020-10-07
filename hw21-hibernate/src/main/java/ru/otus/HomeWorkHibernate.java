package ru.otus;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.Optional;

public class HomeWorkHibernate {
    private static Logger logger = LoggerFactory.getLogger(HomeWorkHibernate.class);

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE, User.class, PhoneDataSet.class, AddressDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        User user = createUser();

        long id = dbServiceUser.saveUser(user);
        Optional<User> mayBeCreatedUser = dbServiceUser.getUser(id);

        user.setName("обновленный Вася");
        user.getPhoneDataSets().get(0).setNumber("+7(800)");
        user.getAddressDataSet().setStreet("Lenina");

        id = dbServiceUser.saveUser(user);
        Optional<User> mayBeUpdatedUser = dbServiceUser.getUser(id);

        outputUserOptional("Created user", mayBeCreatedUser);
        outputUserOptional("Updated user", mayBeUpdatedUser);
    }

    private static User createUser() {
        User user = new User(0, "Вася");

        var listPhone = new ArrayList<PhoneDataSet>();
        for (int idx = 0; idx < 5; idx++) {
            listPhone.add(new PhoneDataSet("+" + idx, user));
        }
        user.setPhoneDataSets(listPhone);
        user.setAddressDataSet(new AddressDataSet("Mira", user));
        return user;
    }

    private static void outputUserOptional(String header, Optional<User> mayBeUser) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(header);
        mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("User not found"));
    }
}
