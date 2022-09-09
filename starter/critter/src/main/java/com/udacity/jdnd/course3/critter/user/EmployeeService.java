package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws PetException {
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSkills(),employeeDTO.getDaysAvailable());
        Employee savedEmployee= employeeRepository.save(employee);

       return new EmployeeDTO(savedEmployee);
    }

    public EmployeeDTO getEmployeeById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return new EmployeeDTO(employee.get());
        }else {
            throw new RuntimeException("Employee not found exception.");
        }
    }


    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, @PathVariable long employeeId){
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()){
            Employee e = employee.get();
            e.setDaysAvailable(daysAvailable);
            employeeRepository.save(e);
        }else {
            throw new RuntimeException("Employee not found exception.");
        }
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO){
        Set<DayOfWeek> day = new HashSet<>();
        day.add(employeeDTO.getDate().getDayOfWeek());
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        List<Employee> employees = employeeRepository.getEmployeeBySkillsInAndDaysAvailableIn(skills,day).stream().filter(employee -> employee.getSkills().containsAll(skills)) .filter(distinctByKey(Person::getId)) .collect(Collectors.toList());
        return  employees.stream().map(EmployeeDTO::new ).collect(Collectors.toList());
    }

}
