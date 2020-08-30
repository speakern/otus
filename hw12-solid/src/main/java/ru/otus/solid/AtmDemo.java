package ru.otus.solid;

import java.util.Map;

public class AtmDemo {
    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.takeInBankNote(BankNote.RUB100, 20);
        atm.takeInBankNote(BankNote.RUB5000, 10);
        atm.takeInBankNote(BankNote.RUB1000, 5);

        //atm.printStorage();
    }
}
