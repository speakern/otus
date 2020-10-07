package ru.otus.listener.homework;

import ru.otus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HistoryMessage {
    static private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSSS");

    private final Message oldMessage;
    private final Message newMessage;
    private final Calendar date;

    public HistoryMessage(Message oldMessage, Message newMessage, Calendar date) {
        this.oldMessage = oldMessage;
        this.newMessage = newMessage;
        this.date = date;
    }

    public Message getOldMessage() {
        return oldMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    public String getTimeToString() {
        return dateFormat.format(this.date.getTime());
    }
}
