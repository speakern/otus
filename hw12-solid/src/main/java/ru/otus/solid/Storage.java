package ru.otus.solid;

import java.util.*;

public class Storage {
    private Map<BankNote, Integer> cells;

    public Storage() {
        cells = new TreeMap<>(Comparator.comparingInt(BankNote::getValue).reversed());
    }

    public void put(BankNote bankNote, int count) {
        cells.put(bankNote, count);
    }

    public Long giveRest() {
        return cells.entrySet().stream().mapToLong(e -> e.getKey().getValue()* e.getValue()).sum();
    }

    public List getExistBill(){
        return new ArrayList(cells.keySet());
    }

    public Map<BankNote, Integer> giveBillSet(Integer amount) {
        Map<BankNote, Integer> billSet = new HashMap<>();
        int currentSum = 0;
        int countCurrentBill = 0;
        int restAmount = amount;

        for (Map.Entry<BankNote, Integer> entry: cells.entrySet()) {
            BankNote currentBill = entry.getKey();
            countCurrentBill = getMaxCountBill(currentBill, restAmount);
            if (countCurrentBill != 0) {
                billSet.put(currentBill, countCurrentBill);
                currentSum = currentSum + currentBill.getValue() * countCurrentBill;
            }
            if (currentSum == amount) {
                return billSet;
            }
            if (currentSum > amount) return null;
            restAmount = amount - currentSum;
        }
        return null;
    }

    private int getMaxCountBill(BankNote bankNote, int amount) {
        int countExistsBill = cells.get(bankNote);  //сколько есть купюр этого номинала
        int countNeedBill =  amount / bankNote.getValue(); // Сколько нужно купюр этого номинала
        return countExistsBill > countNeedBill ? countNeedBill : countExistsBill;
    }

    public void print(){
        for (Map.Entry<BankNote, Integer> entry: cells.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }
    }

    public int giveCountBill(BankNote bankNote) {
        return cells.get(bankNote);
    }
}
