package ru.otus.solid;

import ru.otus.solid.exeptions.NoBanknoteForDeliveryException;

public class Cell {
    private int count;
    private BankNote bankNote;

    public Cell(BankNote bankNote) {
        this.bankNote = bankNote;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add(int addition) {
        count = count + addition;
    }

    public void decrease(int count) {
        if (this.count >= count) {
            this.count = Math.abs(this.count - count);
        } else {
            throw new NoBanknoteForDeliveryException("Нет столько банкнот");
        }
    }

    public int getMaxCountForAmount(int amount) {
        int countNeedBill = amount / bankNote.getValue();
        return count > countNeedBill ? countNeedBill : count;
    }
}
