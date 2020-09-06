package ru.otus.solid.exeptions;

public class NoBanknoteForDeliveryException extends RuntimeException {
    public NoBanknoteForDeliveryException(String message) {
        super(message);
    }
}
