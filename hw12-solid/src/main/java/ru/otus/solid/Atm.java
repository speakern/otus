package ru.otus.solid;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Объект класса АТМ должен уметь:
 * - принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
 * - выдавать запрошенную сумму минимальным количеством банкнот или ошибку, если сумму нельзя выдать
 * Это задание не на алгоритмы, а на проектирование.
 * Поэтому оптимизировать выдачу не надо.
 * - выдавать сумму остатка денежных средств
 * <p>
 * В этом задании больше думайте об архитектуре приложения.
 * Не отвлекайтесь на создание таких объектов как: пользователь, авторизация, клавиатура, дисплей, UI (консольный, Web, Swing), валюта, счет, карта, т.д.
 **/
public class Atm {
    private Storage storage;

    public Atm() {
        storage = new Storage();
    }

    public void takeInBankNote(BankNote bankNote, int count) {
        storage.put(bankNote, count);
    }

    public Map<BankNote, Integer> giveMoney(Integer amount) {
        return null;
    }

    public Long giveRest() {
        return storage.giveRest();
    }

    public void printStorage(){
        storage.print();
        System.out.println(storage.giveCountBill(BankNote.RUB5000));
    }
}
