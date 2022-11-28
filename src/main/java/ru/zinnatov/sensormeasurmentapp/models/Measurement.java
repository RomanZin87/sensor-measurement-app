package ru.zinnatov.sensormeasurmentapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @DecimalMin(value = "-100.0", message = "Value should be between -100.0 and 100.0")
    @DecimalMax(value = "100.0", message = "Value should be between -100.0 and 100.0")
    @Digits(integer = 4, fraction = 2)
    @Column(name = "value")
    private Double value;

    @NotNull
    private Boolean raining;

    @Column(name = "measurement_time")
    @NotNull
    private LocalDateTime measurementTime;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(LocalDateTime measurementTime) {
        this.measurementTime = measurementTime;
    }
}
