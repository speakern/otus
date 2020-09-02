package ru.otus.solid;

import java.util.*;

public class StorageImpl implements Storage {
    private Map<BankNote, Integer> cells;

    public StorageImpl() {
        cells = new TreeMap<>(Comparator.comparingInt(BankNote::getValue).reversed());
    }

    public void put(BankNote bankNote, int count) {
        cells.put(bankNote, count);
    }

    public void reduce(Map<BankNote, Integer> banknoteSet) {
        banknoteSet.entrySet().forEach(e -> {
            cells.replace(e.getKey(), cells.get(e.getKey()) - e.getValue());
        });
    }

    public Long getRest() {
        return cells.entrySet().stream().mapToLong(e -> e.getKey().getValue() * e.getValue()).sum();
    }

    public Map<BankNote, Integer> getBanknoteSetForAmount(Integer amount) {
        Map<BankNote, Integer> billSet = new HashMap<>();
        int currentSum = 0;
        int countOfCurrentBill = 0;
        int currentRestAmount = amount;

        for (Map.Entry<BankNote, Integer> entry : cells.entrySet()) {
            BankNote currentBill = entry.getKey();
            countOfCurrentBill = getMaxCountBillInCellForAmount(currentBill, currentRestAmount);
            if (countOfCurrentBill != 0) {
                billSet.put(currentBill, countOfCurrentBill);
                currentSum = currentSum + currentBill.getValue() * countOfCurrentBill;
            }
            if (currentSum == amount) {
                return billSet;
            }
            if (currentSum > amount) return null;
            currentRestAmount = amount - currentSum;
        }
        return null;
    }

    private int getMaxCountBillInCellForAmount(BankNote bankNote, int amount) {
        int countExistsBill = cells.get(bankNote);  //сколько есть купюр этого номинала
        int countNeedBill = amount / bankNote.getValue(); // Сколько нужно купюр этого номинала
        return countExistsBill > countNeedBill ? countNeedBill : countExistsBill;
    }

    public Set<BankNote> getAllTypeOfBanknote(){
        return cells.keySet();
    }

    public int giveCountBanknote(BankNote bankNote) {
        return cells.get(bankNote);
    }
}
