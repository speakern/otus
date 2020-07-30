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
    private List<Cat> listCat;
    private List<Cat> listCatArray;
    private List<Cat> listCatSource;
    private List<Cat> listCatSourceArray;


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

        listCat = new DIYArrayList();
        listCatArray = new ArrayList();
        fillInCat(listCat, 20, 0);
        fillInCat(listCatArray, 20, 0);

        listCatSource = new DIYArrayList();
        listCatSourceArray = new ArrayList();
        fillInCat(listCatSource, 10, 30);
        fillInCat(listCatSourceArray, 10, 30);

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

    @Test
    void sort() {
        Collections.copy(list, listSource);
        Collections.copy(listArray, listSourceArray);
        Collections.sort(list, NUMBER);
        Collections.sort(listArray, NUMBER);
    }

    @Test
    void addAllCat() {
        Collections.addAll(listCat, new HomeCat("Пушистик"), new HomeCat("Пушистик2"));
        Collections.addAll(listCatArray,  new HomeCat("Пушистик"), new HomeCat("Пушистик2"));
        assertThat(listCat).usingElementComparator(CAT).isEqualTo(listCatArray);
    }

    @Test
    void copyCat() {
        Collections.copy(listCat, listCatSource);
        Collections.copy(listCatArray, listCatSourceArray);
        assertThat(listCat).usingElementComparator(CAT).isEqualTo(listCatArray);
    }

    @Test
    void sortCat() {
        Collections.copy(listCat, listCatSource);
        Collections.copy(listCatArray, listCatSourceArray);
        Collections.sort(listCat, CAT);
        Collections.sort(listCatArray, CAT);
        assertThat(listCat).usingElementComparator(CAT).isEqualTo(listCatArray);
    }

    public static Comparator<String> NUMBER = Comparator.comparingInt(Integer::valueOf);

    public static Comparator<Cat> CAT = Comparator.comparing(Cat::getName);

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