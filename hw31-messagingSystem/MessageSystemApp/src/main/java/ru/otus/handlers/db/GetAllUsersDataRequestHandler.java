package ru.otus.handlers.db;

import ru.otus.domain.User;
import ru.otus.dto.AllUsers;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;
import ru.otus.services.DBServiceUser;

import java.util.Optional;

public class GetAllUsersDataRequestHandler implements RequestHandler<AllUsers> {
    private final DBServiceUser dbService;

    public GetAllUsersDataRequestHandler(DBServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        AllUsers allUsers = MessageHelper.getPayload(msg);
        User data = dbService.getById(user.getId()).orElse(null);
        //UserData data = new UserData(userData.getUserId(), dbService.getById(userData.getUserId()));
        return Optional.of(MessageBuilder.buildReplyMessage(msg, data));
        return Optional.empty();
    }
}
