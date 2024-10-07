package ru.medasukeep.springcourse.SensorTrackingApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.medasukeep.springcourse.SensorTrackingApp.dto.MeasurementDTO;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Measurement;
import ru.medasukeep.springcourse.SensorTrackingApp.services.MeasurementsService;
import ru.medasukeep.springcourse.SensorTrackingApp.services.SensorsService;
import ru.medasukeep.springcourse.SensorTrackingApp.util.GetMeasurementsResponse;
import ru.medasukeep.springcourse.SensorTrackingApp.util.MeasurementDTOValidator;
import ru.medasukeep.springcourse.SensorTrackingApp.util.MeasurementNotAddedException;
import ru.medasukeep.springcourse.SensorTrackingApp.util.ErrorResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private SensorsService sensorsService;
    private MeasurementDTOValidator measurementDTOValidator;
    private ModelMapper modelMapper;
    private MeasurementsService measurementsService;

    @Autowired
    public MeasurementsController(SensorsService sensorsService, MeasurementDTOValidator measurementDTOValidator, ModelMapper modelMapper, MeasurementsService measurementsService) {
        this.sensorsService = sensorsService;
        this.measurementDTOValidator = measurementDTOValidator;
        this.modelMapper = modelMapper;
        this.measurementsService = measurementsService;
    }

    @GetMapping
    public ResponseEntity<GetMeasurementsResponse> getMeasurements() {
        return new ResponseEntity<>(
                new GetMeasurementsResponse(measurementsService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList())),
                HttpStatus.OK
        );
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementDTOValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError fieldError: fieldErrorList) {
                errorMessage.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append("; ");
            }
            throw new MeasurementNotAddedException(errorMessage.toString());
        }
        measurementsService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotAddedException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        measurement.setSensor(sensorsService.findByName(measurementDTO.getSensor().getName()).get(0));
        return measurement;
    }
}
