package ru.otus.listener.homework;

import ru.otus.Message;

public class Pair {
    private Message oldMessage;
    private Message newMessage;

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
