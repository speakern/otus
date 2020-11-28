package ru.otus.services;

import ru.otus.domain.User;
import ru.otus.dto.AllUsers;
import ru.otus.dto.UserData;
import ru.otus.messagesystem.client.MessageCallback;

import java.util.List;

public interface FrontendService {
    void getUserData(long userId, MessageCallback<UserData> dataConsumer);
    void getById(long userId, MessageCallback<User> dataConsumer);
    void createUser(User user, MessageCallback<User> dataConsumer);
    void getAllUser(MessageCallback<AllUsers> dataConsumer);
}

