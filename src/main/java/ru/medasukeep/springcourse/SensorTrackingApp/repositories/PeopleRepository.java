package ru.medasukeep.springcourse.SensorTrackingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medasukeep.springcourse.SensorTrackingApp.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
