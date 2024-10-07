package ru.medasukeep.springcourse.SensorTrackingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Sensor;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
}
