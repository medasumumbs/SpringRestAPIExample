package ru.medasukeep.springcourse.SensorTrackingApp.models;

import javax.persistence.*;
import java.util.List;

/*
 Create table Sensor (
	id int primary key generated by default as identity,
	name varchar(30) not null unique
)
*/
@Entity
@Table(name = "Sensor")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Sensor() {
    }
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurementList;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
