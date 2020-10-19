package ru.otus.services;

import ru.otus.domain.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    long save(User user);

    Optional<User> getById(long id);

    Optional<User> getUserByLogin(String login);

    List<User> getAllUser();
}
