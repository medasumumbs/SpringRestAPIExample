package ru.medasukeep.springcourse.SensorTrackingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Measurement;
import ru.medasukeep.springcourse.SensorTrackingApp.repositories.MeasurementsRepository;
import java.util.List;
import java.time.LocalDateTime;

@Service
@Transactional
public class MeasurementsService {
    private MeasurementsRepository measurementsRepository;
    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }
    public void save(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        measurementsRepository.save(measurement);
    }
    public List<Measurement> findAll() {
        return measurementsRepository.findAllByOrderByCreatedAtAsc();
    }
    public Integer getRainyDaysCount() {
        return measurementsRepository.countDistinctByRaining(true);
    }
}
