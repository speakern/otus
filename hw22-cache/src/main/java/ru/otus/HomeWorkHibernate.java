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
import java.util.List;
import java.util.Optional;

public class HomeWorkHibernate {
    private static Logger logger = LoggerFactory.getLogger(HomeWorkHibernate.class);

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {

        DBServiceUser dbServiceUser = initDbService();

        User user = createUser("Вася");
        List<Long> longList = new ArrayList<>();
        long id;
        int count = 500;  //  Количество вставляемых в БД записей

//Запись в БД
        for (int i = 0; i < count; i++) {
            id = dbServiceUser.saveUser(createUser("Вася" + i));
            longList.add(id);
        }
//Чтение из БД. По логам видно, сколько было обращений к БД.
        for (int i = 0; i < count; i++) {
            dbServiceUser.getUser(longList.get(i));
        }

    }

    private static DBServiceUser initDbService() {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE, User.class, PhoneDataSet.class, AddressDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        return new DbServiceUserImpl(userDao);
    }

    private static User createUser(String name) {
        User user = new User(0, name);

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
