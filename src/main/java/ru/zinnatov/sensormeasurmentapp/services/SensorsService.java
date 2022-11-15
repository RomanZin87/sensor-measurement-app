package ru.zinnatov.sensormeasurmentapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zinnatov.sensormeasurmentapp.models.Sensor;
import ru.zinnatov.sensormeasurmentapp.repositories.SensorsRepository;

import java.util.Optional;

@Service
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public Optional<Sensor> findBySensorName(String sensorName) {
        return sensorsRepository.findByName(sensorName);
    }

    @Transactional
    public Sensor create(Sensor sensor) {
        return sensorsRepository.save(sensor);
    }

}
