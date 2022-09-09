package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.*;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO){
        // get all pets and employees with given id's
        List<Pet> pets = (List<Pet>) petRepository.findAllById(scheduleDTO.getPetIds());
        List<Employee> employees = (List<Employee>) employeeRepository.findAllById(scheduleDTO.getEmployeeIds());
        //create a schedule object
        Schedule schedule = new Schedule(employees,pets,scheduleDTO.getDate(),scheduleDTO.getActivities());
        //save schedule
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleDTO(savedSchedule);
    }

    public List<ScheduleDTO> getAllSchedule(){
        return ((List<Schedule>)scheduleRepository.findAll()).stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForPet(Long petId){
        return scheduleRepository.getScheduleByPetSchedulesIn(petId).stream().map(ScheduleDTO::new).collect(Collectors.toList());

    }

    public List<ScheduleDTO> getScheduleForEmployee(Long employeeId){
        return scheduleRepository.getScheduleByEmployeeSchedulesIn(employeeId).stream().map(ScheduleDTO::new).collect(Collectors.toList());

    }

    public List<ScheduleDTO> getScheduleForCustomer(Long customerId) throws CustomerException{
        Optional<Customer> customer = customerRepository.findById(customerId);
        List<ScheduleDTO> result = new ArrayList<>();
        Customer owner = null;
        if(customer.isPresent()){
            owner = customer.get();
        }else{
            throw new CustomerException("Customer not found exception.");
        }
        owner.getPets().stream().forEach(pet -> {
            result.addAll(getScheduleForPet(pet.getId()));
        });
        return result;
    }


}
