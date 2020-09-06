package ru.otus.solid;

import ru.otus.solid.exeptions.NoBanknoteForDeliveryException;

import java.util.*;

public class StorageImpl implements Storage {
    private Map<BankNote, Cell> cells;

    public StorageImpl() {
        cells = new TreeMap<>(Comparator.comparingInt(BankNote::getValue).reversed());
    }

    public void put(BankNote bankNote, int count) {
        cells.computeIfAbsent(bankNote, key -> new Cell(bankNote)).add(count);
    }

    public void reduce(Map<BankNote, Integer> banknoteSet) {
        banknoteSet.entrySet().forEach(e -> {
            cells.get(e.getKey()).decrease(e.getValue());
        });
    }

    public long getRest() {
        return cells.entrySet().stream().mapToLong(e -> e.getKey().getValue() * e.getValue().getCount()).sum();
    }

    public Map<BankNote, Integer> getBanknoteSetForAmount(Integer amount) {
        Map<BankNote, Integer> billSet = new HashMap<>();
        int currentSum = 0;
        int countOfCurrentBanknote;
        int currentRestAmount = amount;

        for (Map.Entry<BankNote, Cell> entry : cells.entrySet()) {
            BankNote currentBanknote = entry.getKey();
            countOfCurrentBanknote = cells.get(currentBanknote).getMaxCountForAmount(currentRestAmount);
            if (countOfCurrentBanknote != 0) {
                billSet.put(currentBanknote, countOfCurrentBanknote);
                currentSum = currentSum + currentBanknote.getValue() * countOfCurrentBanknote;
            }
            if (currentSum == amount) {
                return billSet;
            }
            if (currentSum > amount) break;
            currentRestAmount = amount - currentSum;
        }
        throw new NoBanknoteForDeliveryException("Нет банкнот для выдачи");
    }

    public Set<BankNote> getAllTypeOfBanknote() {
        return cells.keySet();
    }

    public int giveCountBanknote(BankNote bankNote) {
        return cells.get(bankNote).getCount();
    }
}
