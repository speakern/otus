package ru.otus.io;

public interface AddToJson {
    void addByte();
    void addShort();
    void addInteger();
    void addLong();
    void addBoolean();
    void addChar();
    void addDouble();
    void addFloat();
    void addString();
    Object getObject();
}
