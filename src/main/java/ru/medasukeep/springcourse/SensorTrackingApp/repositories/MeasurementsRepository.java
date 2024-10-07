package ru.medasukeep.springcourse.SensorTrackingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Measurement;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
