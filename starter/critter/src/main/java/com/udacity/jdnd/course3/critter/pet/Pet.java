package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Pet  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    PetType type;
    String notes;
    LocalDate birthDate;
    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    Customer customer;

    @ManyToMany(mappedBy = "petSchedules")
    List<Schedule> schedule;


    public Pet() {
    }

    public Pet(String name, PetType type, String notes,Customer customer, LocalDate birthDate) {
        this.name = name;
        this.type = type;
        this.notes = notes;
        this.customer = customer;
        this.birthDate = birthDate;
    }


    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", notes='" + notes + '\'' +
                ", birthDate=" + birthDate +
                ", customer=" + customer +
                ", schedule=" + schedule +
                '}';
    }
}
