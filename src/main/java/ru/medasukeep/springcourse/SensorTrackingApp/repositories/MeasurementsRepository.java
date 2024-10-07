package ru.medasukeep.springcourse.SensorTrackingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Measurement;
import java.util.List;
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    Integer countDistinctByRaining(Boolean raining);

    List<Measurement> findAllByOrderByCreatedAtAsc();
}
