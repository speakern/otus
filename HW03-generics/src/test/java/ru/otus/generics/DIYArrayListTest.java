package ru.otus.generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.generics.entries.Cat;
import ru.otus.generics.entries.HomeCat;
import ru.otus.generics.entries.WildCat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DIYArrayListTest {

    private List<String> list;
    private List<String> listArray;
    private List<String> listSource;
    private List<String> listSourceArray;

    @BeforeEach
    public void setUp() {
        list = new DIYArrayList();
        listArray = new ArrayList();
        fillIn(list, 20, 0);
        fillIn(listArray, 20, 0);

        listSource = new DIYArrayList();
        listSourceArray = new ArrayList();
        fillIn(listSource, 10, 30);
        fillIn(listSourceArray, 10, 30);

    }

    @Test
    void addAll() {
        Collections.addAll(list, "6", "7");
        Collections.addAll(listArray, "6", "7");
        assertThat(list).usingElementComparator(Comparator.naturalOrder()).isEqualTo(listArray);
    }

    @Test
    void copy() {
        Collections.copy(list, listSource);
        Collections.copy(listArray, listSourceArray);
        assertThat(list).usingElementComparator(Comparator.naturalOrder()).isEqualTo(listArray);
    }

    private static void fillIn(List<String> list, int size, int shift) {
        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i + shift));
        }
    }

    private static void fillInCat(List<Cat> list, int size, int shift) {
        for (int i = 0; i < size; i++) {
            list.add(new HomeCat("Барсик" + (i + shift)));
            list.add(new WildCat("Барсик" + (i + shift)));
        }
    }

}