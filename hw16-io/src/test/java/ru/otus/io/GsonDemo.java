package ru.otus.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonDemo {
    public static void main(String[] args) {
        Gson gson = new Gson();
        BagOfPrimitivesAndArrayList obj = new BagOfPrimitivesAndArrayList(22, "test", 10);

        ArrayList<BagOfPrimitivesAndArrayList> list = new ArrayList<>();
        list.add(obj);
        list.add(obj);
        System.out.println(list);

        String json = gson.toJson(list);
        System.out.println(json);

        Type type = new TypeToken<ArrayList<BagOfPrimitivesAndArrayList>>(){}.getType();
        ArrayList<BagOfPrimitivesAndArrayList> list2 = gson.fromJson(json, type);

        System.out.println(list.equals(list2));
        System.out.println(list2);
    }
}
