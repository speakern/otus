package ru.otus.solid;

import java.util.Map;
import java.util.Set;

public interface Storage {
    void put(BankNote bankNote, int count);

    long getRest();

    Map<BankNote, Integer> getBanknoteSetForAmount(Integer amount);

    Set<BankNote> getAllTypeOfBanknote();

    int giveCountBanknote(BankNote bankNote);

    void reduce(Map<BankNote, Integer> banknoteSet);
}
