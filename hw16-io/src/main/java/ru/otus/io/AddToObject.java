package ru.otus.io;

import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;

public class AddToObject implements AddToJson{
    private JsonObjectBuilder jsonObj;
    private Field field;
    private Object object;

    public AddToObject(JsonObjectBuilder jsonObj, Field field, Object object) {
        this.jsonObj = jsonObj;
        this.field = field;
        this.object = object;
        field.setAccessible(true);
    }

    @Override
    public void addLong() throws IllegalAccessException {
        jsonObj.add(field.getName(), (long) field.get(object));
    }

    @Override
    public void addBoolean() throws IllegalAccessException {
        jsonObj.add(field.getName(), (boolean) field.get(object));
    }

    @Override
    public void addChar() throws IllegalAccessException {
        jsonObj.add(field.getName(), (char) field.get(object));
    }

    @Override
    public void addDouble() throws IllegalAccessException {
        jsonObj.add(field.getName(), (double) field.get(object));
    }

    @Override
    public void addString() throws IllegalAccessException {
        jsonObj.add(field.getName(), (String) field.get(object));
    }

    @Override
    public Object getObject() {
        return object;
    }
}
