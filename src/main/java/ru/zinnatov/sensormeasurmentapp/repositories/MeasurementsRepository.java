package ru.zinnatov.sensormeasurmentapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zinnatov.sensormeasurmentapp.models.Measurement;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    int countByRainingTrue();
}
