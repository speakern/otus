package ru.otus.io;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.*;

public class MyGson {

    public String toJson(Object obj){
        //BagOfPrimitives{value1=22, value2='test', value3=10}
        var jsonObject = Json.createObjectBuilder()
                .add("value1", 22)
                .add("value2", "test")
                .add("value3", 10)
                .add("phoneNumbers",
                        Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("type", "home")
                                        .add("number", "222-222-2222")))
                .build();

 //       System.out.println("jsonObject:" + jsonObject + "\n");

        return jsonObject.toString();
    };

//    private JsonObjectBuilder getJsonObjectBuilder(Object object) {
//
//    }
}