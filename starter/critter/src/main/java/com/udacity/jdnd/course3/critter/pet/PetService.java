package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public PetDTO savePet(PetDTO petDTO) {
        Customer owner = customerRepository.findById(petDTO.getOwnerId()).get();
        Pet pet = petDTO.toEntity(petDTO,owner);
        //add pet to customer
        owner.addPet(pet);
        Pet savedPet = petRepository.save(pet);
        return new PetDTO(savedPet);
    }

    public PetDTO getPet(Long id){
        Pet pet =  petRepository.findById(id).get();
        return new PetDTO(pet);
    }

    public List<PetDTO> getPets(){
        return ((List<Pet>)petRepository.findAll()).stream().map(PetDTO::new).collect(Collectors.toList());
    }


    public List<PetDTO> getPetsByOwner(Long ownerId){
        return (petRepository.getPetsByCustomerId(ownerId)).stream().map(PetDTO::new).collect(Collectors.toList());
    }
}
