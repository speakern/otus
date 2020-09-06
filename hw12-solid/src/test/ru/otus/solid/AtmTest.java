package ru.otus.solid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.solid.exeptions.NoBanknoteForDeliveryException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {

    Atm atm;

    @BeforeEach
    public void setUp() {
        atm = new Atm();
        atm.putBankNote(BankNote.RUB100, 4);
        atm.putBankNote(BankNote.RUB500, 1);
        atm.putBankNote(BankNote.RUB5000, 10);
        atm.putBankNote(BankNote.RUB1000, 10);
    }

    @Test
    void putBankNote() {
        atm.putBankNote(BankNote.RUB1000, 10);
        assertEquals(70900, atm.getRest());
    }

    @Test
    void getMoney() {
        Map<BankNote, Integer> moneySet = atm.getMoney(8900);
        Map<BankNote, Integer> expected = new HashMap<>();
        expected.put(BankNote.RUB5000, 1);
        expected.put(BankNote.RUB1000, 3);
        expected.put(BankNote.RUB500, 1);
        expected.put(BankNote.RUB100, 4);
        assertEquals(moneySet, expected);
    }

    @Test
    void getRest() {
        assertEquals(60900, atm.getRest());
    }

    @Test
    void getNotBanknote() throws Exception {
        Assertions.assertThrows(NoBanknoteForDeliveryException.class,
                () -> atm.getMoney(89000));
    }

    @Test
    void getNotBanknote100() throws Exception {
        atm.getMoney(400);
        Assertions.assertThrows(NoBanknoteForDeliveryException.class,
                () -> atm.getMoney(400));
    }
}