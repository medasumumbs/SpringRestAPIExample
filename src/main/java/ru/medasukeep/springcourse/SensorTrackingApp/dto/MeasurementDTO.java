package ru.medasukeep.springcourse.SensorTrackingApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MeasurementDTO {
    @NotNull
    @Min(value = -100, message = "Температура не должна быть ниже, чем -100 градусов Цельсия")
    @Max(value = 100, message = "Температура не должна превышать 100 градусов Цельсия")
    private Double value;
    @NotNull(message = "Параметр Raining обязательно должен быть передан")
    private Boolean raining;
    @NotNull(message = "Объект сенсора обязательно должен быть передан")
    private SensorDTO sensor;

    private LocalDateTime createdAt;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
