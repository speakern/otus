package ru.otus.solid;

import java.util.Map;

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
        storage = new StorageImpl2();
    }

    public void putBankNote(BankNote bankNote, int count) {
        storage.put(bankNote, count);
    }

    public Map<BankNote, Integer> getMoney(Integer amount) {
        Map<BankNote, Integer> result = storage.getBanknoteSetForAmount(amount);
        storage.reduce(result);
        return result;
    }

    public Long getRest() {
        return storage.getRest();
    }

    public void printAtm(){
        storage.getAllTypeOfBanknote()
                .forEach(bankNote ->
                        System.out.println(bankNote + " " + storage.giveCountBanknote(bankNote)));
    }
}
