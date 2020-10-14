package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.SessionFactory;
import ru.otus.dao.UserDao;
import ru.otus.dao.UserDaoHibernate;
import ru.otus.utils.HibernateUtils;
import ru.otus.model.AddressDataSet;
import ru.otus.model.PhoneDataSet;
import ru.otus.model.User;
import ru.otus.server.UsersWebServer;
import ru.otus.server.UsersWebServerWithFilterBasedSecurity;
import ru.otus.services.*;
import ru.otus.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerWithFilterBasedSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) throws Exception {

        DBServiceUser dbServiceUser = getServiceUser();
        initDB(dbServiceUser);

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(dbServiceUser);
        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, dbServiceUser, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    private static DBServiceUser getServiceUser() {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE, User.class, PhoneDataSet.class, AddressDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);

        return new DbServiceUserImpl(userDao);
    }

    private static void initDB(DBServiceUser dbServiceUser) {
        dbServiceUser.saveUser(createUser("Вася", "vasia"));
        dbServiceUser.saveUser(createUser("Иван", "ivan"));
    }

    private static User createUser(String name, String login) {
        User user = new User(0, name, login, login);

        var listPhone = new ArrayList<PhoneDataSet>();
        for (int idx = 0; idx < 5; idx++) {
            listPhone.add(new PhoneDataSet("+" + idx, user));
        }
        user.setPhoneDataSets(listPhone);
        user.setAddressDataSet(new AddressDataSet("Mira", user));
        return user;
    }

}
