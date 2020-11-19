package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;
import ru.otus.domain.Message;
import ru.otus.domain.PhoneDataSet;
import ru.otus.domain.User;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.services.FrontendService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.otus.ApplConfig.DATE_TIME_FORMAT;

@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final FrontendService frontendService;
    private final SimpMessagingTemplate template;

    public MessageController(FrontendService frontendService, SimpMessagingTemplate template) {
        this.frontendService = frontendService;
        this.template = template;
    }

    @MessageMapping("/message.{roomId}")
    //@SendTo("/topic/response.{roomId}")
    public Message getMessage(@DestinationVariable String roomId, Message message) {
        logger.info("got message:{}, roomId:{}", message, roomId);

        long userId = Long.parseLong(message.getMessageStr());
        frontendService.getById(userId, new MessageCallback<User>() {

            @Override
            public void accept(User data) {
                if (data != null) {
                    doNullUserReference(data);
                    template.convertAndSend("/topic/response.1", data);
                }
            }
        });

        return new Message(HtmlUtils.htmlEscape(message.getMessageStr()));
    }

    @GetMapping("/message")
    public String userListView() {
        return "index.html";
    }

    private void doNullUserReference(User user) {
        List<PhoneDataSet> phoneList = user.getPhoneDataSets();
        for (PhoneDataSet p : phoneList) {
            p.setUser(null);
        }
        if (user.getAddressDataSet() != null) user.getAddressDataSet().setUser(null);
    }

}
