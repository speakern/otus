package ru.otus.processor.homework;

import ru.otus.Message;
import ru.otus.processor.Processor;

import java.util.Calendar;

public class ExceptionProcessor implements Processor {
    //todo: 3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
    private final Processor processor;
    private Seconds seconds;

    public ExceptionProcessor(Processor processor, Seconds seconds) {
        this.processor = processor;
        this.seconds = seconds;
    }

    @Override
    public Message process(Message message) {
        if ((seconds.getCurrentSeconds() % 2) == 0) {
            throw new EvenSecondsException("Четная секунда");
        }
        return processor.process(message);
    }
}
