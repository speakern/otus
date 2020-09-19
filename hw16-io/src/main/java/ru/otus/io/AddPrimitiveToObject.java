package ru.otus.io;

import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;

public class AddPrimitiveToObject implements AddToPrimitive {
    private JsonObjectBuilder jsonObj;
    private Field field;
    private Object object;

    public AddPrimitiveToObject(JsonObjectBuilder jsonObj, Field field, Object object) throws IllegalAccessException {
        this.jsonObj = jsonObj;
        this.field = field;
        field.setAccessible(true);
        this.object = object;
    }

    @Override
    public void addByte() {
        jsonObj.add(field.getName(), (byte) object);
    }

    @Override
    public void addShort() {
        jsonObj.add(field.getName(), (short) object);
    }

    @Override
    public void addInteger(){
        jsonObj.add(field.getName(), (int) object);
    }

    @Override
    public void addLong(){
        jsonObj.add(field.getName(), (long) object);
    }

    @Override
    public void addBoolean(){
        jsonObj.add(field.getName(), (boolean) object);
    }

    @Override
    public void addChar(){
        Character ch = (Character) object;
        jsonObj.add(field.getName(), ch.toString());
    }

    @Override
    public void addDouble(){
        jsonObj.add(field.getName(), (double) object);
    }

    @Override
    public void addFloat() {
        jsonObj.add(field.getName(), (float) object);
    }

    @Override
    public void addString(){
        jsonObj.add(field.getName(), (String) object);
    }

    @Override
    public Object getObject() {
        return object;
    }
}
