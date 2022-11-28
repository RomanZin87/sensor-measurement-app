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
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorDTO sensor;

    @NotNull
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
