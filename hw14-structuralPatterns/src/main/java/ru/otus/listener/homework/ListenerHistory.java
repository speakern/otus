package ru.otus.listener.homework;

import ru.otus.Message;
import ru.otus.listener.Listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ListenerHistory implements Listener {
    static private SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmssSSSS");

    private Map<String, Pair> storage;

    public ListenerHistory(Map<String, Pair> storage) {
        this.storage = storage;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        storage.put(getTimeToString(), new Pair(oldMsg, newMsg));
    }

    private String getTimeToString() {
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
