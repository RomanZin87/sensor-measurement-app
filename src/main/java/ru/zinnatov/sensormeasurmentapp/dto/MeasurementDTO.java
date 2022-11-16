package ru.zinnatov.sensormeasurmentapp.dto;

import ru.zinnatov.sensormeasurmentapp.models.Sensor;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @DecimalMin(value = "-100.0", message = "Value should be between -100.0 and 100.0")
    @DecimalMax(value = "100.0", message = "Value should be between -100.0 and 100.0")
    @Digits(integer = 4, fraction = 2)
    private double value;

    @NotNull
    private boolean raining;

    @NotNull
    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
