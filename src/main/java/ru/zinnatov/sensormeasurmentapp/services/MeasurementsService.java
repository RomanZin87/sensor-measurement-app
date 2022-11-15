package ru.zinnatov.sensormeasurmentapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zinnatov.sensormeasurmentapp.models.Measurement;
import ru.zinnatov.sensormeasurmentapp.repositories.MeasurementsRepository;
import ru.zinnatov.sensormeasurmentapp.repositories.SensorsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementRepository, SensorsService sensorsService) {
        this.measurementRepository = measurementRepository;
        this.sensorsService = sensorsService;
    }

    @Transactional
    public Measurement create(Measurement measurement) {
        enrichMeasurement(measurement);
        return measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorsService.findBySensorName(measurement.getSensor().getName()).get());

        measurement.setMeasurementTime(LocalDateTime.now());
    }

    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    public int countByRainyDays() {
        return measurementRepository.countByRainingTrue();
    }
}
