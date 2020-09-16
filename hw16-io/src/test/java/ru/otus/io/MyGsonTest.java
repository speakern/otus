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
    @DisplayName("Тестируем простой объект c примитивами")
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
    @DisplayName("Тестируем простой объект с вложенным объектом")
    void checkSimpleObjectWithNestedObject() {
        BagOfPrimitivesAndObject originalObj = new BagOfPrimitivesAndObject(22, "test", 10, 23.3434);
        System.out.println(originalObj);
        String myJson = myGson.toJson(originalObj);
       // String myJson = gson.toJson(originalObj);
        System.out.println(myJson);

        BagOfPrimitivesAndObject newObj = gson.fromJson(myJson, BagOfPrimitivesAndObject.class);
        System.out.println(newObj);

        assertThat(newObj).isEqualTo(originalObj);
    }

    @Test
    @DisplayName("Тестируем простой объект с вложенным ArrayList")
    void checkSimpleObjectWithNestedArrayList() {
        BagOfPrimitivesAndArray originalObj = new BagOfPrimitivesAndArray(22, "test", 10);
        System.out.println(originalObj);
        //String myJson = myGson.toJson(originalObj);
        String myJson = gson.toJson(originalObj);
        System.out.println(myJson);

        BagOfPrimitivesAndArray newObj = gson.fromJson(myJson, BagOfPrimitivesAndArray.class);
        System.out.println(newObj);

        assertThat(newObj).isEqualTo(originalObj);
    }

    @Test
    @DisplayName("Тестируем примитивный тип")
    void checkSimpleType() {
        int originalIntObj = 23;
        String myJson = myGson.toJson(originalIntObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);
        int newIntObj = gson.fromJson(myJson, int.class);
        assertThat(newIntObj).isEqualTo(originalIntObj);

        short originalShortObj = 23;
        myJson = myGson.toJson(originalShortObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);
        short newShortObj = gson.fromJson(myJson, short.class);
        assertThat(newShortObj).isEqualTo(originalShortObj);

        long originalLongObj = 23;
        myJson = myGson.toJson(originalLongObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);
        long newLongObj = gson.fromJson(myJson, long.class);
        assertThat(newLongObj).isEqualTo(originalLongObj);

        byte originalByteObj = 23;
        myJson = myGson.toJson(originalByteObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);
        long newByteObj = gson.fromJson(myJson, byte.class);
        assertThat(newByteObj).isEqualTo(originalByteObj);

        double originalDoubleObj = 12.23;
        myJson = myGson.toJson(originalDoubleObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);
        double newDoubleObj = gson.fromJson(myJson, double.class);
        assertThat(newDoubleObj).isEqualTo(originalDoubleObj);

        float originalFloatObj = (float) 12.23;
        myJson = myGson.toJson(originalFloatObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);
        float newFloatObj = gson.fromJson(myJson, float.class);
        assertThat(newFloatObj).isEqualTo(originalFloatObj);

        boolean originalBooleanObj = true;
        myJson = myGson.toJson(originalBooleanObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);
        boolean newBooleanObj = gson.fromJson(myJson, boolean.class);
        assertThat(newBooleanObj).isEqualTo(originalBooleanObj);
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
    @DisplayName("Тестируем null")
    void checkNullObject() {
        Object originalObj = null;
        String myJson = myGson.toJson(originalObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);

        Object newObj = gson.fromJson(myJson, Object.class);
        System.out.println(newObj);

        assertThat(newObj).isEqualTo(originalObj);
    }

    @Test
    @DisplayName("Тестируем массив примитивных типов")
    void checkArraySimpleType() {
        int[] originalObj = new int[] { 10, 100 };
        //String[] originalObj = new String[] { "1212", "2323" };
        String myJson = myGson.toJson(originalObj);
        //String myJson = gson.toJson(originalObj);
        System.out.println(myJson);
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

        String json = myGson.toJson(originalList);
        //String json = gson.toJson(originalList);
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