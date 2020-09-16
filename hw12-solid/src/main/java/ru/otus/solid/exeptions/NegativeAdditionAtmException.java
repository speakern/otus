package ru.otus.solid.exeptions;

public class NegativeAdditionAtmException extends AtmException{
    public NegativeAdditionAtmException(String message) {
        super(message);
    }
}
