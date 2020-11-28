package ru.otus.services;

import ru.otus.domain.User;
import ru.otus.dto.AllUsers;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageType;
import ru.otus.messagesystem.client.MsClient;

import java.util.ArrayList;

public class FrontendServiceImpl implements FrontendService {

    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void getById(long userId, MessageCallback<User> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, new User(userId, "", "", ""),
                MessageType.USER, dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void createUser(User user, MessageCallback<User> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, user,
                MessageType.CREATE_USER, dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void getAllUser(MessageCallback<AllUsers> dataConsumer){
        Message outMsg = msClient.produceMessage(databaseServiceClientName, new AllUsers(new ArrayList<>()),
                MessageType.ALL_USERS, dataConsumer);
        msClient.sendMessage(outMsg);
    }
}
