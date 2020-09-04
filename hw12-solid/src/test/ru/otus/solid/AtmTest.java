package ru.otus.solid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        atm.putBankNote(BankNote.RUB1000, 10);
    }

    @Test
    void putBankNote() {
    }

    @Test
    void getMoney() {
        Map<BankNote, Integer> expected = new HashMap<>();
        expected.put("n", "node");
        expected.put("c", "c++");
        expected.put("j", "java");
        expected.put("p", "python");
    }

    @Test
    void getRest() {
        assertEquals(70900, atm.getRest());
    }
}