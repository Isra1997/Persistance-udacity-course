package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.schedule.Schedule;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends Person {
    @Column(name = "daysAvailable", nullable = true, insertable = true, updatable = true)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<EmployeeSkill> skills;
    @Column(name = "daysAvailable", nullable = true, insertable = true, updatable = true)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "employeeSchedules")
    List<Schedule> schedule;

    public Employee( String name, Set<EmployeeSkill> skills,Set<DayOfWeek> daysAvailable) {
        super(name);
        this.daysAvailable = daysAvailable;
        this.skills = skills;
    }


    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public Employee() {
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "skills=" + skills +
                ", daysAvailable=" + daysAvailable +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
