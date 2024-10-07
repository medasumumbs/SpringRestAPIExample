package ru.medasukeep.springcourse.SensorTrackingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    List<Sensor> findByName(String name);
}
