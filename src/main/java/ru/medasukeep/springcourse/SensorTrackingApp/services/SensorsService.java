package ru.medasukeep.springcourse.SensorTrackingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Sensor;
import ru.medasukeep.springcourse.SensorTrackingApp.repositories.SensorsRepository;

import java.util.List;

@Service
@Transactional
public class SensorsService {
    private SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
    public List<Sensor> findByName(String name) {
        return sensorsRepository.findByName(name);
    }

}
