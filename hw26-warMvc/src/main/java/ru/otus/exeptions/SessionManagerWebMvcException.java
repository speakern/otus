package ru.otus.exeptions;


public class SessionManagerWebMvcException extends WebMvcException{
    public SessionManagerWebMvcException(String msg) {
        super(msg);
    }

    public SessionManagerWebMvcException(Exception ex) {
        super(ex);
    }
}
