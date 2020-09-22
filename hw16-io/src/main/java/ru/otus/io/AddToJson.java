package ru.otus.io;

public interface AddToJson {
    void addNull();
    void addPrimitive();
    void addArray();
    void addCollection();
    void addObject();
    Object getObject();
}
