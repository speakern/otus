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
}
