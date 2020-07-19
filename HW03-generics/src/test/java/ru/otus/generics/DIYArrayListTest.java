package ru.otus.generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DIYArrayListTest {

    private static DIYArrayList<String> diyArrayList;

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 20; i++) {
            diyArrayList.add("str" + Integer.toString(i));
        }
    }

    @Test
    void size() {
    }

    @Test
    void add() {
    }

    @Test
    void addAll() {
    }
}