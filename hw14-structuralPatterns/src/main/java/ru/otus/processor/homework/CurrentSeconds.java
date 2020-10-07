package ru.otus.processor.homework;

import java.util.Calendar;

public class CurrentSeconds implements Seconds {
    @Override
    public long getCurrentSeconds() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }
}
