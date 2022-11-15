package ru.zinnatov.sensormeasurmentapp.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Sensor's name should not be empty")
    @Size(min = 3, max = 30, message = "Sensor's name should contains 3-30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
