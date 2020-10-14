package ru.otus.dao;

import ru.otus.model.User;
import ru.otus.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);

    long insertUser(User user);

    void updateUser(User user);

    Optional<User> findByLogin(String login);

    void insertOrUpdate(User user);

    List<User> findAll();

    SessionManager getSessionManager();
}
