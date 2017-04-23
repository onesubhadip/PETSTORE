package org.petstore;

import java.util.List;

import org.petstore.model.Pet;
import org.petstore.util.PetStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;
	
	public Pet getPetDetails(Long petId){
		Pet pet = petRepository.findByPetId(petId);
		return pet;
	}
	
	public List<Pet> getPetDetailsByStatus(String status){
		if(status.equalsIgnoreCase(PetStatus.ALL.name())){
			return petRepository.findAll();
		}
		else{
			return petRepository.findByStatusContainingIgnoreCase(status);
		}
	}
	
	public Pet savePetToStore(Pet pet){
		return petRepository.save(pet);
	}
	
	public Pet removePet(Long petId){
		Pet pet = petRepository.findByPetId(petId);
		if(pet != null && petRepository.removeByPetId(petId) == 1){
			return pet;
		}
		return null;
	}
}
