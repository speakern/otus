package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerPrinter;
import ru.otus.listener.homework.ListenerHistory;
import ru.otus.listener.homework.Pair;
import ru.otus.processor.ProcessorUpperField10;
import ru.otus.processor.homework.ExceptionProcessor;
import ru.otus.processor.homework.ProcessorExchangeFields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13
       2. Сделать процессор, который поменяет местами значения field11 и field13
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
        var processors = List.of(new ProcessorExchangeFields(),
                new ExceptionProcessor(new ProcessorUpperField10()));

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            System.out.println(ex);
        });
        Map<String, Pair> storage = new HashMap<>();
        var listenerPrinter = new ListenerPrinter();
        var listenerHistory = new ListenerHistory(storage);
        complexProcessor.addListener(listenerPrinter);
        complexProcessor.addListener(listenerHistory);

        var message1 = new Message.Builder()
                .field10("field10")
                .field11("field11")
                .field13("field13")
                .build();

        var message2 = new Message.Builder()
                .field10("2field10")
                .field11("2field11")
                .field13("2field13")
                .build();

        complexProcessor.handle(message1);
        complexProcessor.handle(message2);

        storage.entrySet().forEach(e -> System.out.println(String.format("oldMsg:%s, newMsg:%s",
                e.getValue().getOldMessage(), e.getValue().getNewMessage())));

        complexProcessor.removeListener(listenerPrinter);
        complexProcessor.removeListener(listenerHistory);
    }
}
