package ru.otus.io;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
Cвой json object writer
        Цель: Научиться сериализовывать объект в json, попрактиковаться в разборе структуры объекта.
        Напишите свой json object writer (object to JSON string) аналогичный gson на основе javax.json.

        Gson это делает так:
        Gson gson = new Gson();
        AnyObject obj = new AnyObject(22, "test", 10);
        String json = gson.toJson(obj);

        Сделайте так:
        MyGson myGson = new MyGson();
        AnyObject obj = new AnyObject(22, "test", 10);
        String myJson = myGson.toJson(obj);

        Должно получиться:
        AnyObject obj2 = gson.fromJson(myJson, AnyObject.class);
        System.out.println(obj.equals(obj2));

        Поддержите:
        - примитивные типы и Wrapper-ы (Integer, Float и т.д.)
        - строки
        - массивы примитивных типов
        - коллекции (interface Collection)

        Не забываться, что obj может быть null :)
**/

public class MyGsonDemo {
    public static void main(String[] args) {
        //BagOfPrimitives obj = new BagOfPrimitives(22, "test", 10, 343.233);
        int[] obj = {10,100};

        Class<? extends Object> clazz = obj.getClass();
        Field[] fieldsAll = clazz.getDeclaredFields();
        Arrays.stream(fieldsAll).forEach(field -> {
            System.out.println(field.getType() + "    " + field.getName() + "  "
                    + field.getType().isPrimitive());
           // Integer.TYPE.
        });

//        int[] array = {10,100};


        //int[] array1 =  new int[10];

    }
}
