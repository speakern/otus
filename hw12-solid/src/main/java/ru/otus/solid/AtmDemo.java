package ru.otus.solid;

import java.util.Map;

public class AtmDemo {
    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.putBankNote(BankNote.RUB100, 20);
        atm.putBankNote(BankNote.RUB500, 3);
        atm.putBankNote(BankNote.RUB5000, 10);
        atm.putBankNote(BankNote.RUB1000, 2);

        Map<BankNote, Integer> moneySet =  atm.getMoney(8900);

        for (Map.Entry<BankNote, Integer> entry: moneySet.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }
        atm.printAtm();
    }
}
