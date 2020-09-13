package ru.otus.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class MyGsonTest {
    Gson gson = new Gson();
    MyGson myGson = new MyGson();

    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("Тестируем простой объект")
    void checkSimpleObject() {
        BagOfPrimitives originalObj = new BagOfPrimitives(22, "test", 10, 23.3434);
        String myJson = myGson.toJson(originalObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);

        BagOfPrimitives newObj = gson.fromJson(myJson, BagOfPrimitives.class);
        System.out.println(newObj);

        assertThat(newObj).isEqualTo(originalObj);
    }

    @Test
    @DisplayName("Тестируем примитивный тип")
    void checkSimpleType() {
        int originalObj = 23;
//        String myJson = myGson.toJson(originalObj);
        String myJson = gson.toJson(originalObj);
        System.out.println(myJson);

        int newObj = gson.fromJson(myJson, int.class);

        assertThat(newObj).isEqualTo(originalObj);
    }

    @Test
    @DisplayName("Тестируем Wrapper")
    void checkWrapperType() {
        Integer originalIntegerObj = 23;
        String myJsonInteger = myGson.toJson(originalIntegerObj);

        String originalStringObj = "Строка";
        String myJsonString = myGson.toJson(originalStringObj);

        Boolean originalBooleanObj = true;
        String myJsonBoolean = myGson.toJson(originalBooleanObj);

        Integer newObjInteger = gson.fromJson(myJsonInteger, Integer.class);
        String newObjString = gson.fromJson(myJsonString, String.class);
        Boolean newObjBoolean = gson.fromJson(myJsonBoolean, Boolean.class);

        assertThat(newObjString).isEqualTo(originalStringObj);
        assertThat(newObjInteger).isEqualTo(originalIntegerObj);
        assertThat(newObjBoolean).isEqualTo(originalBooleanObj);
    }

    @Test
    @DisplayName("Тестируем массив примитивных типов")
    void checkArraySimpleType() {
        int[] originalObj = new int[] { 10, 100 };
        String myJson = myGson.toJson(originalObj);
        int[] newObj = gson.fromJson(myJson, int[].class);

        assertThat(newObj).isEqualTo(originalObj);
    }

    @Test
    @DisplayName("Тестируем коллекции: ArrayList")
    void checkCollectionType() {
        BagOfPrimitivesAndArray obj = new BagOfPrimitivesAndArray(22, "test", 10);

        ArrayList<BagOfPrimitivesAndArray> originalList = new ArrayList<>();
        originalList.add(obj);
        originalList.add(obj);
        System.out.println(originalList);

  //      String json = myGson.toJson(originalList);
        String json = gson.toJson(originalList);
        System.out.println(json);

        Type type = new TypeToken<ArrayList<BagOfPrimitivesAndArray>>(){}.getType();
        ArrayList<BagOfPrimitivesAndArray> newList = gson.fromJson(json, type);

        System.out.println(originalList.equals(newList));
        System.out.println(newList);

        assertThat(newList).isEqualTo(originalList);
    }

//    @Test
//    void copy() {
//        Collections.copy(list, listSource);
//        Collections.copy(listArray, listSourceArray);
//        assertThat(list).usingElementComparator(Comparator.naturalOrder()).isEqualTo(listArray);
//    }

}