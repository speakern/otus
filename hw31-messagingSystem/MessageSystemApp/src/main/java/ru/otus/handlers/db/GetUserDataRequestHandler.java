package ru.otus.handlers.db;

import ru.otus.domain.User;
import ru.otus.dto.UserData;
import ru.otus.messagesystem.message.MessageHelper;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.services.DBServiceUser;

import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler<User> {
    private final DBServiceUser dbService;

    public GetUserDataRequestHandler(DBServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        UserData userData = MessageHelper.getPayload(msg);
        User data = dbService.getById(userData.getUserId()).orElse(null);
        //UserData data = new UserData(userData.getUserId(), dbService.getById(userData.getUserId()));
        return Optional.of(MessageBuilder.buildReplyMessage(msg, data));
    }
}
