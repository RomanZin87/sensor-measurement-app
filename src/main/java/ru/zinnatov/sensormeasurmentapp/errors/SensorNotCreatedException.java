package ru.zinnatov.sensormeasurmentapp.errors;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String msg) {
        super(msg);
    }
}
