package ru.medasukeep.springcourse.SensorTrackingApp.util;

public class MeasurementNotAddedException extends RuntimeException {
    public MeasurementNotAddedException(String message) {
        super(message);
    }
}
