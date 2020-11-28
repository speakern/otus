package ru.otus.services;

import org.springframework.stereotype.Service;
import ru.otus.domain.User;
import ru.otus.dto.AllUsers;
import ru.otus.dto.UserData;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageType;
import ru.otus.messagesystem.client.MsClient;

import java.util.ArrayList;
import java.util.List;

public class FrontendServiceImpl implements FrontendService {

    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void getUserData(long userId, MessageCallback<UserData> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, new UserData(userId),
                MessageType.USER_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
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
                MessageType.CREATEUSER, dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void getAllUser(MessageCallback<AllUsers> dataConsumer){
        Message outMsg = msClient.produceMessage(databaseServiceClientName, new AllUsers(new ArrayList<>()),
                MessageType.ALLUSERS, dataConsumer);
        msClient.sendMessage(outMsg);
    }
}
