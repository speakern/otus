package ru.otus.solid;

public interface Cell {
    int getCount();

    void add(int addition);

    void decrease(int count);

    int getMaxCountForAmount(int amount);
}
