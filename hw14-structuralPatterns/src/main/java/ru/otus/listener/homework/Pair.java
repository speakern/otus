package ru.otus.listener.homework;

import ru.otus.Message;

public class Pair {
    private final Message oldMessage;
    private final Message newMessage;

    public Pair(Message oldMessage, Message newMessage) {
        this.oldMessage = oldMessage;
        this.newMessage = newMessage;
    }

    public Message getOldMessage() {
        return oldMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }
}
