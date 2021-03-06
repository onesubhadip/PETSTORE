package org.petstore;

import java.util.List;

import org.petstore.exception.PetServiceExcption;
import org.petstore.model.Pet;
import org.petstore.util.PetStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
/*
 * Pet business service implementation class. Depends on PetReposotory to fetch data from DB.
 */
@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;

	public Pet getPetDetails(Long petId) {
		try {
			Pet pet = petRepository.findByPetId(petId);
			return pet;
		} catch (DataAccessException dao) {
			throw new PetServiceExcption(dao);
		}

	}

	public List<Pet> getAllPets() {
		try {
			return petRepository.findAll();
		} catch (DataAccessException dao) {
			throw new PetServiceExcption(dao);
		}
	}

	public List<Pet> getPetDetailsByStatus(String status) {
		try {
			if (status.equalsIgnoreCase(PetStatus.ALL.name())) {
				return petRepository.findAll();
			} else {
				return petRepository.findByStatusContainingIgnoreCase(status);
			}
		} catch (DataAccessException dao) {
			throw new PetServiceExcption(dao);
		}
	}

	public List<Pet> getPetDetailsByCategories(List<String> categories) {
		try {
			if (categories.size() > 0) {
				return petRepository.findByCategory_nameIn(categories);
			} else {
				return null;
			}
		} catch (DataAccessException dao) {
			throw new PetServiceExcption(dao);
		}
	}

	public Pet savePetToStore(Pet pet) {
		try {
			return petRepository.save(pet);
		} catch (DataAccessException dao) {
			throw new PetServiceExcption(dao);
		}
	}

	public Pet removePet(Long petId) {
		try {
			Pet pet = petRepository.findByPetId(petId);
			if (pet != null && petRepository.removeByPetId(petId) == 1) {
				return pet;
			}
			return null;
		} catch (DataAccessException dao) {
			throw new PetServiceExcption(dao);
		}
	}
}
