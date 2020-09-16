package ru.otus.io;

public interface AddToJson {
    void addLong() throws IllegalAccessException;
    void addBoolean() throws IllegalAccessException;
    void addChar() throws IllegalAccessException;
    void addDouble() throws IllegalAccessException;
    void addString() throws IllegalAccessException;
    Object getObject();
}
