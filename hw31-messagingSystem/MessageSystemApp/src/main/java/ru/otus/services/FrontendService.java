package ru.otus.services;

import ru.otus.domain.User;
import ru.otus.dto.AllUsers;
import ru.otus.messagesystem.client.MessageCallback;


public interface FrontendService {
    void getById(long userId, MessageCallback<User> dataConsumer);
    void createUser(User user, MessageCallback<User> dataConsumer);
    void getAllUser(MessageCallback<AllUsers> dataConsumer);
}

