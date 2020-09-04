package ru.otus.solid;

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
        this.count = Math.abs(this.count - count);
    }

    public int getMaxCountForAmount(int amount) {
        int countNeedBill = amount / bankNote.getValue();
        return count > countNeedBill ? countNeedBill : count;
    }
}
