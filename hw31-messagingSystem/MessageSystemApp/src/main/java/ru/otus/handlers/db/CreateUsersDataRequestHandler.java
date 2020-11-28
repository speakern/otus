package ru.otus.handlers.db;

import ru.otus.domain.User;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;
import ru.otus.services.DBServiceUser;

import java.util.Optional;

public class CreateUsersDataRequestHandler implements RequestHandler<User> {
    private final DBServiceUser dbService;


    public CreateUsersDataRequestHandler(DBServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        User user = MessageHelper.getPayload(msg);
        Long userId = dbService.save(user);
        User data = dbService.getById(userId).orElse(null);
        return Optional.of(MessageBuilder.buildReplyMessage(msg, data));
    }
}
