package ru.otus.solid;

import java.util.Map;

public interface Atm {
    void putBankNote(BankNote bankNote, int count);

    Map<BankNote, Integer> getMoney(Integer amount);

    Long getRest();
}
