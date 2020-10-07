package ru.otus.io;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.*;

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
        obj.getClass().isArray();
        List<String> list = new ArrayList<>();

        System.out.println(list instanceof Collection);

        //System.out.println(obj.getClass() instanceof Iterable);
        //System.out.println(map instanceof Collection);

        Class[] intfs = list.getClass().getInterfaces();

        Arrays.stream(intfs).forEach(intf -> {
            System.out.println(intf.getTypeName());
           // Integer.TYPE.
        });

//        int[] array = {10,100};


        //int[] array1 =  new int[10];

        //{"value1":22,"value2":"test","value3":10,"value4":23.3434,"bagOfPrimitives":{"value1":22,"value2":"test","value3":20,"value4":23.12121212}}
//        jsonObject = Json.createObjectBuilder()
//                .add("value1", 22)
//                .add("value2", "test")
//                .add("value3", 10)
//                .add("value4", 23.3434)
//                .add("bagOfPrimitives",
//                        Json.createObjectBuilder()
//                                .add("value1", 22)
//                                .add("value2", "test")
//                                .add("value3", 10)
//                                .add("value4", 23.3434));
//        jsonObject = Json.createObjectBuilder()
//                .add("value1", 22)
//                .add("value2", "test")
//                .add("value3", 10)
//                .add("bagOfPrimitives",
//                        Json.createArrayBuilder()
//                                .add(Json.createObjectBuilder()
//                                        .add("type", "home")
//                                        .add("number", "222-222-2222")))
        //              .build();

        //       System.out.println("jsonObject:" + jsonObject + "\n");
    }
}
