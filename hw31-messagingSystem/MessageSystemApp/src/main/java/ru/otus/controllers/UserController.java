package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.domain.User;
import ru.otus.dto.UserForm;
import ru.otus.services.DBServiceUser;

import java.util.List;

@Controller
public class UserController {

    private final DBServiceUser usersService;

    public UserController(DBServiceUser usersService) {
        this.usersService = usersService;
    }

    @GetMapping({"/", "/user/list"})
    public String userListView(Model model) {
        List<User> users = usersService.getAllUser();
        model.addAttribute("users", users);
        return "users.html";
    }

}
