package ru.otus.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.PhoneDataSet;
import ru.otus.domain.User;
import ru.otus.services.DBServiceUser;

import java.util.List;

@RestController
public class UserRestController {

    private final DBServiceUser usersService;

    public UserRestController(DBServiceUser usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        User user = usersService.getById(id).orElse(null);
        if (user != null) doNullUserReference(user);
        return user;
    }

    private void doNullUserReference(User user) {
        List<PhoneDataSet> phoneList = user.getPhoneDataSets();
        phoneList.forEach(p -> p.setUser(null));
        if (user.getAddressDataSet() != null) user.getAddressDataSet().setUser(null);
    }

}
