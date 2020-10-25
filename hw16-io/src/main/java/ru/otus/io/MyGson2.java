package ru.otus.io;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class MyGson2 {

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
        for (Object currentObject: object) {
            addToArray(jsonArray, currentObject);
        }
        return jsonArray;
    }

    private JsonArrayBuilder createArrayToJson(Object object) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        int lengthArray = Array.getLength(object);
        for (int i = 0; i < lengthArray; i++) {
            addToArray(jsonArray, Array.get(object, i));
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
                addToObject(jsonObj, field, field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObj;
    }

    private void addToObject(JsonObjectBuilder jsonObj, Field field, Object object) throws IllegalAccessException {
        if (object == null) {
            jsonObj.add(field.getName(), JsonValue.NULL);

        } else if (isPrimitive(object.getClass())) {
            addPrimitiveTo(new AddPrimitiveToObject(jsonObj, field, object));

        } else if (object.getClass().isArray()) {
            addArrayToObject(jsonObj, field, object);

        } else if (object instanceof Collection) {
            addCollectionToObject(jsonObj, field, (Collection) object);

        } else {
            addObjectToObject(jsonObj, field, object);
        }
    }

    private void addToArray(JsonArrayBuilder jsonArray, Object object){
        if (object == null) {
            jsonArray.add(JsonValue.NULL);

        } else if (isPrimitive(object.getClass())) {
            addPrimitiveTo(new AddPrimitiveToArray(jsonArray, object));

        } else if (object.getClass().isArray()) {
            addArrayToArray(jsonArray, object);

        } else if (object instanceof Collection) {
            addCollectionToArray(jsonArray, (Collection) object);

        } else {
            addObjectToArray(jsonArray, object);
        }
    }

    private void addObjectToObject(JsonObjectBuilder jsonObj, Field objectField, Object object) {
        jsonObj.add(objectField.getName(), createObjectToJson(object));
    }

    private void addArrayToObject(JsonObjectBuilder jsonObj, Field objectField, Object object) {
        jsonObj.add(objectField.getName(), createArrayToJson(object));
    }

    private void addCollectionToObject(JsonObjectBuilder jsonObj, Field objectField, Collection collection) {
        jsonObj.add(objectField.getName(), createCollectionToJson(collection));
    }

    private void addObjectToArray(JsonArrayBuilder jsonArray, Object object) {
        jsonArray.add(createObjectToJson(object));
    }

    private void addArrayToArray(JsonArrayBuilder jsonArray, Object object) {
        jsonArray.add(createArrayToJson(object));
    }

    private void addCollectionToArray(JsonArrayBuilder jsonArray, Collection object) {
        jsonArray.add(createCollectionToJson(object));
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

    private void addPrimitiveTo(AddToPrimitive addPrimitiveToJson){
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