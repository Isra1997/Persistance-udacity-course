package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetException;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public CustomerDTO saveCustomer(CustomerDTO customerDto) throws PetException{
        Customer customer = new Customer(customerDto.getName(), customerDto.getPhoneNumber());
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerDTO(savedCustomer);
    }

    public List<CustomerDTO> getAll(){
        return ((List<Customer>) customerRepository.findAll()).stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    public CustomerDTO getOwnerByPet(Long petId){
        Customer customer = customerRepository.getCustomerByPets_Id(petId);
        return new CustomerDTO(customer);
    }

}
