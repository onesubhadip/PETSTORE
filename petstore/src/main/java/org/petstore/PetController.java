package org.petstore;

import java.util.List;

import org.petstore.exception.PetNotFoundException;
import org.petstore.model.ErrorResponse;
import org.petstore.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/pet")
@Api(tags = {"Pet resources"})
public class PetController {
	@Autowired
	private PetService petService;

	@ApiOperation(value = "Get a single pet details by pet id.", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pet found", response = Pet.class),
			@ApiResponse(code = 404, message = "Pet Not Found") })
	@GetMapping(value = "/{petId}", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Pet getPetDetails(
			@ApiParam(required = true, value = "Id of the pet in the store") @PathVariable("petId") Long petId) {
		Pet pet = petService.getPetDetails(petId);
		if (pet == null){
			throw new PetNotFoundException();
		}
		return pet;
	}

	@ApiOperation(value = "List of pets for given status.", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Pet.class),
			@ApiResponse(code = 404, message = "No Pet for the selected status.") })
	@GetMapping(value = "/status/{status}", produces = "application/json")
	@ResponseStatus(code=HttpStatus.OK)
	public ResponseEntity<List<Pet>> listPetDetails(
			@ApiParam(allowableValues = "all,available,sold", defaultValue = "available", required = true, value = "Inventory status to look for") @PathVariable("status") String status) {
		
		List<Pet> pets = petService.getPetDetailsByStatus(status);
		if (pets == null || pets.isEmpty()){
			throw new PetNotFoundException();
		}
		return ResponseEntity.ok(pets);
	}

	@ApiOperation(value = "Add a new pet to the store.", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Pet saved", response = Pet.class),
			@ApiResponse(code = 400, message = "Invalid input for new Pet") })
	@PostMapping(value = "/", consumes = "application/json")
	@ResponseStatus(code=HttpStatus.CREATED)
	public ResponseEntity<Pet> addPetDetails(
			@ApiParam(value = "Pet details in JSON fromat", required = true) @Validated @RequestBody(required = true) Pet petDetails) {

		Pet pet = petService.savePetToStore(petDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(pet);
	}

	@ApiOperation(value = "Remove a pet from the store.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pet removed", response = Pet.class),
			@ApiResponse(code = 404, message = "Pet Not Found") })
	@DeleteMapping(value = "/{petId}")
	@ResponseStatus(code=HttpStatus.OK)
	public ResponseEntity<Pet> removePetDetails(
			@ApiParam(required = true, value = "Id of the pet to remove") @PathVariable("petId") Long petId) {
		
		Pet pet = petService.removePet(petId);
		if (pet == null){
			throw new PetNotFoundException();
		}
		return ResponseEntity.ok(pet);
	}

	@ExceptionHandler(PetNotFoundException.class)
	public ResponseEntity<ErrorResponse> NotFoundexceptionHandler() {
		
		ErrorResponse error = new ErrorResponse();
		error.setErrorMessage("No such pet in the store inventory.");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
}
