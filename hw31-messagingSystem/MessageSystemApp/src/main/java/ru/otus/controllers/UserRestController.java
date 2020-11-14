package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.PhoneDataSet;
import ru.otus.domain.User;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.services.DBServiceUser;
import ru.otus.services.FrontendService;

import java.util.List;

@RestController
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final DBServiceUser usersService;
    private final FrontendService frontendService;
    final User[] user = {null};


    public UserRestController(DBServiceUser usersService, FrontendService frontendService) {
        this.usersService = usersService;
        this.frontendService = frontendService;
    }

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        //User user = usersService.getById(id).orElse(null);
        //final User[] user = {null};
        //if (user != null) doNullUserReference(user);

        //frontendService.getById(1, data -> logger.info("got data:{}", data));
        frontendService.getById(1, new MessageCallback<User>() {
            @Override
            public void accept(User data) {
                user[0] = data;
            }
        });
//        frontendService.getUserData(5, data -> logger.info("got data:{}", data));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (user[0] != null) doNullUserReference(user[0]);

        return user[0];
    }

    private void doNullUserReference(User user) {
        List<PhoneDataSet> phoneList = user.getPhoneDataSets();
        phoneList.forEach(p -> p.setUser(null));
        if (user.getAddressDataSet() != null) user.getAddressDataSet().setUser(null);
    }

}
