package ru.medasukeep.springcourse.SensorTrackingApp.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30, message = "Длина поля name должна составлять не менее 3 и не более 30 символов")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
