package ru.medasukeep.springcourse.SensorTrackingApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.medasukeep.springcourse.SensorTrackingApp.dto.SensorDTO;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Sensor;
import ru.medasukeep.springcourse.SensorTrackingApp.services.SensorsService;

import java.util.List;

@Component
public class SensorDTOValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public SensorDTOValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(SensorDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) o;
        List<Sensor> sensorList = sensorsService.findByName(sensorDTO.getName());
        if (!sensorList.isEmpty()) {
            errors.rejectValue("name","", "Сенсор с таким именем уже зарегистрирован");
        }

    }
}
