package ru.otus.solid;

import ru.otus.solid.exeptions.NegativeAdditionAtmException;
import ru.otus.solid.exeptions.NoBanknoteForDeliveryAtmException;

public class BestCell implements Cell {
    private int count;
    private BankNote bankNote;

    public BestCell(BankNote bankNote) {
        this.bankNote = bankNote;
    }

    public int getCount() {
        return count;
    }

    public void add(int addition) {
        if (addition < 0) throw new NegativeAdditionAtmException("Нельзя добавить отрицательное количество купюр!");
        count = count + addition;
    }

    public void decrease(int count) {
        if (this.count >= count) {
            this.count =- count;
        } else {
            throw new NoBanknoteForDeliveryAtmException("Нет столько банкнот");
        }
    }

    public int getMaxCountForAmount(int amount) {
        int countNeedBill = amount / bankNote.getValue();
        return count > countNeedBill ? countNeedBill : count;
    }
}
