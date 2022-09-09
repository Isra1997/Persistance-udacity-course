package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule,Long> {

    @Query("SELECT s " +
            "FROM Schedule s JOIN s.petSchedules c " +
            "WHERE c.id = :petId")
    List<Schedule> getScheduleByPetSchedulesIn(Long petId);
    @Query("SELECT s " +
            "FROM Schedule s JOIN s.employeeSchedules c " +
            "WHERE c.id = :employeeId")
    List<Schedule> getScheduleByEmployeeSchedulesIn(Long employeeId);



}
