package ru.otus.io;

import com.google.gson.Gson;

public class MyGsonDemo {
    public static void main(String[] args) {
    //    Gson это делает так:
        Gson gson = new Gson();
//        BagOfPrimitives obj = new BagOfPrimitives(22, "test", 10);
//        String json = gson.toJson(obj);

    //    Сделайте так:
        MyGson myGson = new MyGson();
        BagOfPrimitives obj = new BagOfPrimitives(22, "test", 10);
        String myJson = myGson.toJson(obj);

    //    Должно получиться:
        BagOfPrimitives obj2 = gson.fromJson(myJson, BagOfPrimitives.class);
        System.out.println(obj.equals(obj2));
    }
}
