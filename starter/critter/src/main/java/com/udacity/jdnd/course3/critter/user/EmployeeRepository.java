package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    List<Employee> getEmployeeBySkillsInAndDaysAvailableIn(Set<EmployeeSkill> skills,Set<DayOfWeek> day);
}
