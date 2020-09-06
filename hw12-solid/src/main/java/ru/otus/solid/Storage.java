package ru.otus.solid;

import java.util.Map;
import java.util.Set;

public interface Storage {
    public void put(BankNote bankNote, int count);

    public long getRest();

    public Map<BankNote, Integer> getBanknoteSetForAmount(Integer amount);

    public Set<BankNote> getAllTypeOfBanknote();

    public int giveCountBanknote(BankNote bankNote);

    public void reduce(Map<BankNote, Integer> banknoteSet);
}
