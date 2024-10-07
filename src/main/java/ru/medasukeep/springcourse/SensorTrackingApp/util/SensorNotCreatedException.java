package ru.medasukeep.springcourse.SensorTrackingApp.util;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String msg) {
        super(msg);
    }
}
