package ru.otus.processor.homework;

import ru.otus.Message;
import ru.otus.processor.Processor;

import java.util.Calendar;

public class ExceptionProcessor implements Processor {
    //todo: 3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
    private final Processor processor;

    public ExceptionProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Message process(Message message) {
        int currentSecond =  Calendar.getInstance().get(Calendar.SECOND);
        if ((currentSecond % 2) == 0) {
            throw new RuntimeException("Четная секунда");
        }
        return processor.process(message);
    }
}
