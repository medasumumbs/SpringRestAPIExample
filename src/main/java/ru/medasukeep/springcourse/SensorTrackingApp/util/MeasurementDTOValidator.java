package ru.medasukeep.springcourse.SensorTrackingApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.medasukeep.springcourse.SensorTrackingApp.dto.MeasurementDTO;
import ru.medasukeep.springcourse.SensorTrackingApp.services.SensorsService;

@Component
public class MeasurementDTOValidator implements Validator {
    private SensorsService sensorsService;
    @Autowired
    public MeasurementDTOValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) o;
        if (measurementDTO.getSensor() != null) {
            if (sensorsService.findByName(measurementDTO.getSensor().getName()).isEmpty()) {
                errors.rejectValue("sensor.name","","Сенсор с именем " + measurementDTO.getSensor().getName() + " не зарегистрирован в базе");
            }
        }
    }
}
