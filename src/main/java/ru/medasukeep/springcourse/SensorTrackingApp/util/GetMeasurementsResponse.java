package ru.medasukeep.springcourse.SensorTrackingApp.util;

import ru.medasukeep.springcourse.SensorTrackingApp.dto.MeasurementDTO;

import java.util.List;

public class GetMeasurementsResponse {
    private List<MeasurementDTO> measurementsList;

    public GetMeasurementsResponse(List<MeasurementDTO> measurementsList) {
        this.measurementsList = measurementsList;
    }

    public List<MeasurementDTO> getMeasurementsList() {
        return measurementsList;
    }

    public void setMeasurementsList(List<MeasurementDTO> measurementsList) {
        this.measurementsList = measurementsList;
    }
}
