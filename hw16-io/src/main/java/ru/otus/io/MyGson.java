package ru.otus.io;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class MyGson {
    private JsonObjectBuilder jsonObject;
    //private Object object;

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
             addPrimitiveMemberToJsonArray(jsonArray, Array.get(object, i));
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
                    addPrimitiveFieldToJson(jsonObj, field, object);
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

    private void addPrimitiveFieldToJson(JsonObjectBuilder jsonObj, Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        if ((field.getType() == int.class) || (field.getType() == Integer.class)
                || (field.getType() == long.class) || (field.getType() == Long.class)
                || (field.getType() == byte.class) || (field.getType() == Byte.class)
                || (field.getType() == short.class) || (field.getType() == Short.class))
        {
            jsonObj.add(field.getName(), (long) field.get(object));
        }
        if ((field.getType() == boolean.class) || (field.getType() == Boolean.class)) {
            jsonObj.add(field.getName(), (boolean) field.get(object));
        }
        if ((field.getType() == char.class) || (field.getType() == Character.class)) {
            jsonObj.add(field.getName(), (char) field.get(object));
        }
        if ((field.getType() == double.class) || (field.getType() == Double.class)
                || (field.getType() == float.class) || (field.getType() == Float.class))
        {
            jsonObj.add(field.getName(), (double) field.get(object));
        }
        if (field.getType() == String.class) {
            jsonObj.add(field.getName(), (String) field.get(object));
        }
    }


//    private void addPrimitiveTo(AddToJson objToJson) throws IllegalAccessException {
//
//        if ((objToJson.getClass() == int.class) || (field.getType() == Integer.class)
//        || (field.getType() == long.class) || (field.getType() == Long.class)
//        || (field.getType() == byte.class) || (field.getType() == Byte.class)
//        || (field.getType() == short.class) || (field.getType() == Short.class))
//        {
//            jsonObj.add(field.getName(), (long) field.get(object));
//        }
//        if ((field.getType() == boolean.class) || (field.getType() == Boolean.class)) {
//            jsonObj.add(field.getName(), (boolean) field.get(object));
//        }
//        if ((field.getType() == char.class) || (field.getType() == Character.class)) {
//            jsonObj.add(field.getName(), (char) field.get(object));
//        }
//        if ((field.getType() == double.class) || (field.getType() == Double.class)
//        || (field.getType() == float.class) || (field.getType() == Float.class))
//        {
//            jsonObj.add(field.getName(), (double) field.get(object));
//        }
//        if (field.getType() == String.class) {
//            jsonObj.add(field.getName(), (String) field.get(object));
//        }
//    }

    private void addPrimitiveMemberToJsonArray(JsonArrayBuilder jsonArray, Object object) {
        if ((object.getClass() == int.class) || (object.getClass() == Integer.class)) {
            jsonArray.add((int) object);
        }
//        if ((field.getType() == long.class) || (field.getType() == Long.class)){
//            jsonObj.add(field.getName(), (long) field.get(object));
//        }
//        if ((field.getType() == byte.class) || (field.getType() == Byte.class)) {
//            jsonObj.add(field.getName(), (byte) field.get(object));
//        }
//        if ((field.getType() == short.class) || (field.getType() == Short.class)) {
//            jsonObj.add(field.getName(), (short) field.get(object));
//        }
//        if ((field.getType() == boolean.class) || (field.getType() == Boolean.class)) {
//            jsonObj.add(field.getName(), (boolean) field.get(object));
//        }
//        if ((field.getType() == char.class) || (field.getType() == Character.class)) {
//            jsonObj.add(field.getName(), (char) field.get(object));
//        }
//        if ((field.getType() == float.class) || (field.getType() == Float.class)) {
//            jsonObj.add(field.getName(), (float) field.get(object));
//        }
//        if ((field.getType() == double.class) || (field.getType() == Double.class)) {
//            jsonObj.add(field.getName(), (double) field.get(object));
//        }
//        if (field.getType() == String.class) {
//            jsonObj.add(field.getName(), (String) field.get(object));
//        }
    }

    ;

//    private JsonObjectBuilder getJsonObjectBuilder(Object object) {
//
//    }
}