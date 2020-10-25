package ru.otus.exeptions;

public class WebMvcException extends RuntimeException {
    public WebMvcException(Exception ex) {
        super(ex);
    }
}
