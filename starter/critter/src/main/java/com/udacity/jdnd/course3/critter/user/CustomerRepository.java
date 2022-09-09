package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface
CustomerRepository extends CrudRepository<Customer,Long> {
        Customer getCustomerByPets_Id(Long petId);
}
