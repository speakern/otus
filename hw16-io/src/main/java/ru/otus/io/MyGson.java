package ru.otus.io;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class MyGson {

    public String toJson(Object obj)  {
        if (obj == null) {
            return null;
        }
        if (isPrimitive(obj.getClass())) {
            return obj.toString();

        } else if (obj.getClass().isArray()) {
            return createArrayToJson(obj).build().toString();

        } else if (obj instanceof Collection) {
            return createCollectionToJson((Collection) obj).build().toString();

        } else {
            return createObjectToJson(obj).build().toString();
        }
    }

    private JsonArrayBuilder createCollectionToJson(Collection object) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (Object obj: object) {
            addPrimitiveTo(new AddToArray(jsonArray, obj));
        }
        return jsonArray;
    }

    private JsonArrayBuilder createArrayToJson(Object object) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        int lengthArray = Array.getLength(object);
        for (int i = 0; i < lengthArray; i++) {
            if (isPrimitive(object.getClass().getComponentType())) {
                addPrimitiveTo(new AddToArray(jsonArray, Array.get(object, i)));
            } else {
                addObjectToJson(jsonArray, Array.get(object, i));
            }
        }
        return jsonArray;
    }

    private JsonObjectBuilder createObjectToJson(Object object) {
        JsonObjectBuilder jsonObj = Json.createObjectBuilder();
        Class<? extends Object> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (isPrimitive(field.getType())) {
                    addPrimitiveTo(new AddToObject(jsonObj, field, object));
                } else if (field.getType().isArray()) {
                    addArrayToJson(jsonObj, field, field.get(object));
                }
                else {
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

    private JsonArrayBuilder addObjectToJson(JsonArrayBuilder jsonObj, Object object) {
        JsonArrayBuilder jsonArrayBuilder = jsonObj.add(createObjectToJson(object));
        return jsonArrayBuilder;
    }

    private JsonObjectBuilder addArrayToJson(JsonObjectBuilder jsonObj, Field objectField, Object object) {
        JsonObjectBuilder jsonObjectBuilder = jsonObj.add(objectField.getName(), createArrayToJson(object));
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