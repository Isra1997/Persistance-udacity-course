package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToMany(targetEntity = Employee.class)
    @JoinTable(
            name = "employee_schedule",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    List<Employee> employeeSchedules;
    @ManyToMany(targetEntity = Pet.class)
    @JoinTable(
            name = "pet_schedule",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    List<Pet> petSchedules;
    LocalDate date;

    @ElementCollection
    Set<EmployeeSkill> activities;

    public Schedule() {
    }

    public Schedule(List<Employee> employeeSchedules, List<Pet> petSchedules, LocalDate date, Set<EmployeeSkill> activities) {
        this.employeeSchedules = employeeSchedules;
        this.petSchedules = petSchedules;
        this.date = date;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployeeSchedules() {
        return employeeSchedules;
    }

    public void setEmployeeSchedules(List<Employee> employeeSchedules) {
        this.employeeSchedules = employeeSchedules;
    }

    public List<Pet> getPetSchedules() {
        return petSchedules;
    }

    public void setPetSchedules(List<Pet> petSchedules) {
        this.petSchedules = petSchedules;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }


}
