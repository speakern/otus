package ru.otus.io;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class MyGson {

    public String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        if (isPrimitive(obj.getClass())) {
            return obj.toString();
        }
        return makeJson(new CreateObjectToJson(obj, this));
    }

    JsonArrayBuilder createCollectionToJson(Collection object) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (Object currentObject : object) {
            makeJson(new AddMemberToArrayToJson(jsonArray, currentObject, this));
        }
        return jsonArray;
    }

    JsonArrayBuilder createArrayToJson(Object object) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        int lengthArray = Array.getLength(object);
        for (int i = 0; i < lengthArray; i++) {
            makeJson(new AddMemberToArrayToJson(jsonArray, Array.get(object, i), this));
        }
        return jsonArray;
    }

    JsonObjectBuilder createObjectToJson(Object object) {
        JsonObjectBuilder jsonObj = Json.createObjectBuilder();
        Class<? extends Object> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                makeJson(new AddFieldToObjectToJson(jsonObj, field, field.get(object), this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObj;
    }

    private String makeJson(AddToJson addToJson) {

        Object object = addToJson.getObject();

        if (object == null) {
            addToJson.addNull();

        } else if (isPrimitive(object.getClass())) {
            addToJson.addPrimitive();

        } else if (object.getClass().isArray()) {
            addToJson.addArray();

        } else if (object instanceof Collection) {
            addToJson.addCollection();

        } else {
            addToJson.addObject();
        }
        return addToJson.toString();
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

    void addPrimitiveTo(AddToPrimitive addPrimitiveToJson) {
        Class<? extends Object> clazz = addPrimitiveToJson.getObject().getClass();
        if (clazz == Byte.class) {
            addPrimitiveToJson.addByte();
        }
        if (clazz == Short.class) {
            addPrimitiveToJson.addShort();
        }
        if (clazz == Integer.class) {
            addPrimitiveToJson.addInteger();
        }
        if (clazz == Long.class) {
            addPrimitiveToJson.addLong();
        }
        if (clazz == Double.class) {
            addPrimitiveToJson.addDouble();
        }
        if (clazz == Float.class) {
            addPrimitiveToJson.addFloat();
        }
        if (clazz == Character.class) {
            addPrimitiveToJson.addChar();
        }
        if (clazz == Boolean.class) {
            addPrimitiveToJson.addBoolean();
        }
        if (clazz == String.class) {
            addPrimitiveToJson.addString();
        }
    }

}