package ru.otus.controllers;

import org.springframework.web.bind.annotation.*;
import ru.otus.domain.User;
import ru.otus.services.DBServiceUser;

@RestController
public class UserRestController {

    private final DBServiceUser usersService;

    public UserRestController(DBServiceUser usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        return usersService.getById(id).get();
    }
//
//    @GetMapping("/api/user")
//    public User getUserByName(@RequestParam(name = "name") String name) {
//        return usersService.findByName(name);
//    }
//
//    @PostMapping("/api/user")
//    public User saveUser(@RequestBody User user) {
//        return usersService.save(user);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/api/user/random")
//    public User findRandom() {
//        return usersService.findRandom();
//    }

}