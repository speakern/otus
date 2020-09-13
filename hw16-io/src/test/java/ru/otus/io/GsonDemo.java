package ru.otus.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GsonDemo {
    public static void main(String[] args) {
        Gson gson = new Gson();
        BagOfPrimitivesAndArray obj = new BagOfPrimitivesAndArray(22, "test", 10);

        ArrayList<BagOfPrimitivesAndArray> list = new ArrayList<>();
        list.add(obj);
        list.add(obj);
        System.out.println(list);

        String json = gson.toJson(list);
        System.out.println(json);

        Type type = new TypeToken<ArrayList<BagOfPrimitivesAndArray>>(){}.getType();
        ArrayList<BagOfPrimitivesAndArray> list2 = gson.fromJson(json, type);

        System.out.println(list.equals(list2));
        System.out.println(list2);
    }
}
