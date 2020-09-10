package ru.otus.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyGsonTest {

    private List<String> list;
    private List<String> listArray;
    private List<String> listSource;
    private List<String> listSourceArray;

    @BeforeEach
    public void setUp() {
//        list = new Gson();
//        listArray = new ArrayList();
//        fillIn(list, 20, 0);
//        fillIn(listArray, 20, 0);
//
//        listSource = new Gson();
//        listSourceArray = new ArrayList();
//        fillIn(listSource, 10, 30);
//        fillIn(listSourceArray, 10, 30);
//
//        listCat = new Gson();
//        listCatArray = new ArrayList();
//        fillInCat(listCat, 20, 0);
//        fillInCat(listCatArray, 20, 0);
//
//        listCatSource = new Gson();
//        listCatSourceArray = new ArrayList();
//        fillInCat(listCatSource, 10, 30);
//        fillInCat(listCatSourceArray, 10, 30);

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
//        Collections.addAll(listCat, new HomeCat("Пушистик"), new HomeCat("Пушистик2"));
//        Collections.addAll(listCatArray,  new HomeCat("Пушистик"), new HomeCat("Пушистик2"));
//        assertThat(listCat).usingElementComparator(CAT).isEqualTo(listCatArray);
    }

    @Test
    void copyCat() {
//        Collections.copy(listCat, listCatSource);
//        Collections.copy(listCatArray, listCatSourceArray);
//        assertThat(listCat).usingElementComparator(CAT).isEqualTo(listCatArray);
    }

    @Test
    void sortCat() {
//        Collections.copy(listCat, listCatSource);
//        Collections.copy(listCatArray, listCatSourceArray);
//        Collections.sort(listCat, CAT);
//        Collections.sort(listCatArray, CAT);
//        assertThat(listCat).usingElementComparator(CAT).isEqualTo(listCatArray);
    }

    public static Comparator<String> NUMBER = Comparator.comparingInt(Integer::valueOf);

 //   public static Comparator<Cat> CAT = Comparator.comparing(Cat::getName);

    private static void fillIn(List<String> list, int size, int shift) {
        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i + shift));
        }
    }
}