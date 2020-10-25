package ru.otus.exeptions;

public class DbServiceWebMvcException extends WebMvcException {
    public DbServiceWebMvcException(Exception e) {
        super(e);
    }
}
