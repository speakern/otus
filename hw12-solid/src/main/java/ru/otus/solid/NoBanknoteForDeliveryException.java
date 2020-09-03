package ru.otus.solid;

public class NoBanknoteForDeliveryException extends RuntimeException {
    public NoBanknoteForDeliveryException(String message) {
        super(message);
    }
}
