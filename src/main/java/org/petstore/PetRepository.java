package org.petstore;

import java.util.List;

import org.petstore.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPARepository interface implementation to create custom queries using method names.
 * Implementation of this class is not required.
 */
@Transactional(readOnly = true)
@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

	public Pet findByPetId(Long petId);
	public List<Pet> findByStatusContainingIgnoreCase(String status);
	public List<Pet> findByCategory_nameIn(List<String> categories);
	@Transactional @Modifying public Integer removeByPetId(Long petId);
}
