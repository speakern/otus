package ru.otus.io;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class MyGson {

    public String toJson(Object obj)  {
        if (obj == null) {
            return null;
        }
        if (isPrimitive(obj.getClass())) {
            return obj.toString();
        } else if (obj.getClass().isArray()) {
            return createArrayToJson(obj).build().toString();
        } else {
            return createObjectToJson(obj).build().toString();
        }
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
    private JsonArrayBuilder createArrayToJson(Object object) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        int lengthArray = Array.getLength(object);
        for (int i = 0; i < lengthArray; i++) {
             //addPrimitiveMemberToJsonArray(jsonArray, Array.get(object, i));
            addPrimitiveTo(new AddToArray(jsonArray, Array.get(object, i)));
        }
        return jsonArray;
    }

    private JsonObjectBuilder createObjectToJson(Object object) {
        JsonObjectBuilder jsonObj = Json.createObjectBuilder();
        Class<? extends Object> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (isPrimitive(field.getType())) {
                    //addPrimitiveFieldToJson(jsonObj, field, object);
                    addPrimitiveTo(new AddToObject(jsonObj, field, object));
                } else {
                    field.setAccessible(true);
                    addObjectToJson(jsonObj, field, field.get(object));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObj;
    }

    private JsonObjectBuilder addObjectToJson(JsonObjectBuilder jsonObj, Field objectField, Object object) {
        JsonObjectBuilder jsonObjectBuilder = jsonObj.add(objectField.getName(), createObjectToJson(object));
        return jsonObjectBuilder;
    }

    private boolean isPrimitive(Class<? extends Object> clazz) {
        if (clazz.isPrimitive()
                || clazz == Integer.class
                || clazz == Long.class
                || clazz == Byte.class
                || clazz == Short.class
                || clazz == Boolean.class
                || clazz == Character.class
                || clazz == Float.class
                || clazz == Double.class
                || clazz == String.class) {
            return true;
        }
        return false;
    }

    private void addPrimitiveTo(AddToJson addPrimitiveToJson){
        Class<? extends Object> clazz = addPrimitiveToJson.getObject().getClass();
        if (clazz == Byte.class){
            addPrimitiveToJson.addByte();
        }
        if (clazz == Short.class){
            addPrimitiveToJson.addShort();
        }
        if (clazz == Integer.class){
            addPrimitiveToJson.addInteger();
        }
        if (clazz == Long.class){
            addPrimitiveToJson.addLong();
        }
        if (clazz == Double.class){
            addPrimitiveToJson.addDouble();
        }
        if (clazz == Float.class){
            addPrimitiveToJson.addFloat();
        }
        if (clazz == Character.class){
            addPrimitiveToJson.addChar();
        }
        if (clazz == Boolean.class){
            addPrimitiveToJson.addBoolean();
        }
        if (clazz == String.class){
            addPrimitiveToJson.addString();
        }
    }

}