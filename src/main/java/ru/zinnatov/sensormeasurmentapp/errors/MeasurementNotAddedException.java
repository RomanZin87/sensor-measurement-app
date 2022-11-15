package ru.zinnatov.sensormeasurmentapp.errors;

public class MeasurementNotAddedException extends RuntimeException {
    public MeasurementNotAddedException(String msg) {
        super(msg);
    }
}
