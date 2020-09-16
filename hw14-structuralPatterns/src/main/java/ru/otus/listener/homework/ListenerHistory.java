package ru.otus.listener.homework;

import ru.otus.Message;
import ru.otus.listener.Listener;

import java.util.Calendar;
import java.util.Map;

public class ListenerHistory implements Listener {
    private Map<Long, HistoryMessage> storage;
    private long Id;

    public ListenerHistory(Map<Long, HistoryMessage> storage) {
        this.storage = storage;
        this.Id = 0;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        storage.put(this.Id++, new HistoryMessage(oldMsg, newMsg, Calendar.getInstance()));
    }
}
