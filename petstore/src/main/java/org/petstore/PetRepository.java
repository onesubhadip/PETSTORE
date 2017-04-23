package org.petstore;

import java.util.List;

import org.petstore.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PetRepository extends JpaRepository<Pet, Long>{

	public Pet findByPetId(Long petId);
	public List<Pet> findByStatusContainingIgnoreCase(String status);
	@Transactional @Modifying public Integer removeByPetId(Long petId);
}
