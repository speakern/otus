package ru.otus.solid;

public enum BankNote {
    RUB5000(5000),
    RUB1000(1000),
    RUB500(500),
    RUB100(100);

    private final int value;

    private BankNote(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
