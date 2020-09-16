package ru.otus.processor.homework;

import ru.otus.Message;
import ru.otus.processor.Processor;

public class ProcessorExchangeFields implements Processor {

    //todo: 2. Сделать процессор, который поменяет местами значения field11 и field13

    @Override
    public Message process(Message message) {
        return message.toBuilder()
                .field11(message.getField13())
                .field13(message.getField11())
                .build();
    }
}