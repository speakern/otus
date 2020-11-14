package ru.otus.services;

import ru.otus.domain.User;
import ru.otus.dto.UserData;
import ru.otus.messagesystem.client.MessageCallback;

public interface FrontendService {
    void getUserData(long userId, MessageCallback<UserData> dataConsumer);
    void getById(long userId, MessageCallback<User> dataConsumer);
}

