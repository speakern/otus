package ru.otus.solid;

import java.util.Map;

public class AtmDemo {
    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.putBankNote(BankNote.RUB100, 4);
        atm.putBankNote(BankNote.RUB500, 1);
        atm.putBankNote(BankNote.RUB5000, 10);
        atm.putBankNote(BankNote.RUB1000, 10);
        atm.putBankNote(BankNote.RUB1000, 10);
        System.out.println("остаток:" + atm.getRest());

        System.out.println("Получить 8900");
        Map<BankNote, Integer> moneySet = atm.getMoney(8900);

        System.out.println("Получены следующие банкноты:");
        for (Map.Entry<BankNote, Integer> entry : moneySet.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }
        System.out.println("Остатки банкнот:");
        atm.printAtm();
    }
}
