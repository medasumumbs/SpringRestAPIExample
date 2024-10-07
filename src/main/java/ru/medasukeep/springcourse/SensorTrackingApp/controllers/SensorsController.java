package ru.medasukeep.springcourse.SensorTrackingApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.medasukeep.springcourse.SensorTrackingApp.dto.SensorDTO;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Sensor;
import ru.medasukeep.springcourse.SensorTrackingApp.services.SensorsService;
import ru.medasukeep.springcourse.SensorTrackingApp.util.SensorDTOValidator;
import ru.medasukeep.springcourse.SensorTrackingApp.util.ErrorResponse;
import ru.medasukeep.springcourse.SensorTrackingApp.util.SensorNotCreatedException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private SensorsService sensorsService;
    private ModelMapper modelMapper;

    private SensorDTOValidator sensorDTOValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper, SensorDTOValidator sensorDTOValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorDTOValidator = sensorDTOValidator;
    }
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorDTOValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError fieldError: fieldErrorList) {
                errorMessage.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append("; ");
            }
            throw new SensorNotCreatedException(errorMessage.toString());
        }
        sensorsService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
