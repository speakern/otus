package ru.otus.solid.exeptions;

public class NoBanknoteForDeliveryAtmException extends AtmException {
    public NoBanknoteForDeliveryAtmException(String message) {
        super(message);
    }
}
