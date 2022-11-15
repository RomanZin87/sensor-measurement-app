package ru.zinnatov.sensormeasurmentapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zinnatov.sensormeasurmentapp.dto.SensorDTO;
import ru.zinnatov.sensormeasurmentapp.errors.SensorNotCreatedException;
import ru.zinnatov.sensormeasurmentapp.models.Sensor;
import ru.zinnatov.sensormeasurmentapp.services.SensorsService;
import ru.zinnatov.sensormeasurmentapp.utils.ErrorResponse;
import ru.zinnatov.sensormeasurmentapp.utils.SensorValidator;
import ru.zinnatov.sensormeasurmentapp.utils.ValidationUtils;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class SensorController {

    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(ModelMapper modelMapper, SensorsService sensorsService, SensorValidator sensorValidator) {
        this.modelMapper = modelMapper;
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping(value = "/sensors/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sensor> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = ValidationUtils.getErrorMsg(bindingResult);
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        Sensor created = sensorsService.create(sensor);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/sensors/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }



    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
