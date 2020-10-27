package ru.otus.exeptions;

public class WebMvcException extends RuntimeException {
    public WebMvcException(String msg) {
        super(msg);
    }
    public WebMvcException(Exception ex) {
        super(ex);
    }
}
