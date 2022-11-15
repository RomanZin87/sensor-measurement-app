package ru.zinnatov.sensormeasurmentapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.zinnatov.sensormeasurmentapp.models.Measurement;
import ru.zinnatov.sensormeasurmentapp.services.SensorsService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if(measurement.getSensor()==null) {
            return;
        }

        if(sensorsService.findBySensorName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "There is no sensor with this name");
        }
    }
}
