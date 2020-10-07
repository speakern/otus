package ru.otus.io;

import javax.json.JsonArrayBuilder;

public class AddToArray implements AddToJson{
    private JsonArrayBuilder jsonArray;
    private Object object;

    public AddToArray(JsonArrayBuilder jsonArray, Object object) {
        this.jsonArray = jsonArray;
        this.object = object;
    }

    @Override
    public void addByte() {
        jsonArray.add((byte) object);
    }

    @Override
    public void addShort() {
        jsonArray.add((short) object);
    }

    @Override
    public void addInteger() {
        jsonArray.add((int) object);
    }

    @Override
    public void addLong() {
        jsonArray.add((long) object);
    }

    @Override
    public void addBoolean() {
        jsonArray.add((boolean) object);
    }

    @Override
    public void addChar() {
        Character ch = (Character) object;
        jsonArray.add(ch.toString());
    }

    @Override
    public void addDouble() {
        jsonArray.add((double) object);
    }

    @Override
    public void addFloat() {
        jsonArray.add((float) object);
    }

    @Override
    public void addString() {
        jsonArray.add((String) object);
    }

    @Override
    public Object getObject() {
        return object;
    }
}
