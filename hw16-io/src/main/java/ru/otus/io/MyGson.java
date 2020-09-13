package ru.otus.io;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;

public class MyGson {
    private JsonObjectBuilder jsonObject;
    private Object object;

    public String toJson(Object obj)  {
        jsonObject = Json.createObjectBuilder();
        this.object = obj;

        Class<? extends Object> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            try {
                //if isPrimitive(field)
                addPrimitiveFieldToJson(field);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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

        return jsonObject.build().toString();
    }

    private boolean isPrimitive(Field field) {
//        if (field.getType().isPrimitive() ||  field.getType().getComponentType().
        return true;
    }

    private void addPrimitiveFieldToJson(Field field) throws IllegalAccessException {
        field.setAccessible(true);
        if ((field.getType() == int.class) || (field.getType() == Integer.class)) {
            jsonObject.add(field.getName(), (int) field.get(object));
        }
        if ((field.getType() == long.class) || (field.getType() == Long.class)){
            jsonObject.add(field.getName(), (long) field.get(object));
        }
        if ((field.getType() == byte.class) || (field.getType() == Byte.class)) {
            jsonObject.add(field.getName(), (byte) field.get(object));
        }
        if ((field.getType() == short.class) || (field.getType() == Short.class)) {
            jsonObject.add(field.getName(), (short) field.get(object));
        }
        if ((field.getType() == boolean.class) || (field.getType() == Boolean.class)) {
            jsonObject.add(field.getName(), (boolean) field.get(object));
        }
        if ((field.getType() == char.class) || (field.getType() == Character.class)) {
            jsonObject.add(field.getName(), (char) field.get(object));
        }
        if ((field.getType() == float.class) || (field.getType() == Float.class)) {
            jsonObject.add(field.getName(), (float) field.get(object));
        }
        if ((field.getType() == double.class) || (field.getType() == Double.class)) {
            jsonObject.add(field.getName(), (double) field.get(object));
        }
        if (field.getType() == String.class) {
            jsonObject.add(field.getName(), (String) field.get(object));
        }
    }

    ;

//    private JsonObjectBuilder getJsonObjectBuilder(Object object) {
//
//    }
}