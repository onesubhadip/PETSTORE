package org.petstore;

import java.util.List;

import org.petstore.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/pet")
@Api(value = "PetStore API")
public class PetController {
	@Autowired
	private PetService petService;

	@ApiOperation(value = "Get a single pet details by pet id.", produces="application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pet found", response = Pet.class),
			@ApiResponse(code = 400, message = "Invalid Id Supplied"),
			@ApiResponse(code = 404, message = "Pet Not Found") })
	@GetMapping(value = "/{petId}", produces="application/json")
	public Pet getPetDetails(@ApiParam(required=true, value="Id of the pet in the store") @PathVariable("petId") Long petId) {
		return petService.getPetDetails(petId);
	}

	@ApiOperation(value = "List of pets for given status.", produces="application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Pet.class),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/status/{status}", produces="application/json")
	public List<Pet> listPetDetails(
			@ApiParam(allowableValues = "all,available,sold", defaultValue = "available", required=true, value="Inventory status to look for") 
			@PathVariable("status") String status) {
		return petService.getPetDetailsByStatus(status);
	}

	@ApiOperation(value = "Add a new pet to the store.", consumes="application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Pet.class),
			@ApiResponse(code = 405, message = "Invalid input")})
	@PostMapping(value = "/", consumes="application/json")
	public Pet addPetDetails(@ApiParam(value="Pet details in JSON fromat", required=true) @RequestBody(required=true) Pet petDetails) {
		return petService.savePetToStore(petDetails);
	}

	@ApiOperation(value = "Remove a pet from the store.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pet removed", response = Pet.class),
			@ApiResponse(code = 400, message = "Invalid Id Supplied"),
			@ApiResponse(code = 404, message = "Pet Not Found") })
	@DeleteMapping(value = "/{petId}")
	public Pet removePetDetails(@ApiParam(required=true, value="Id of the pet to remove") @PathVariable("petId") Long petId) {
		return petService.removePet(petId);
	}
}
