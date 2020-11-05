package ru.otus.exeptions;

public class UserDaoWebMvcException extends WebMvcException {
    public UserDaoWebMvcException(Exception ex) {
        super(ex);
    }
}
