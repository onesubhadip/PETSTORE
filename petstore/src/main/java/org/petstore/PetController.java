package org.petstore;

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
@RequestMapping("/pet")
@Api(value = "PetStore API")
public class PetController {

	@ApiOperation(value = "Get a single pet details by pet id.", produces="application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pet found", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Id Supplied", response = String.class),
			@ApiResponse(code = 404, message = "Pet Not Found", response = String.class) })
	@GetMapping(value = "/{petId}", produces="application/json")
	public String getPetDetails(@ApiParam(required=true, value="Id of the pet in the store") @PathVariable("petId") Long petId) {
		return "Dog";
	}

	@ApiOperation(value = "List of pets for given status.", produces="application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class) })
	@GetMapping(value = "/status/{status}", produces="application/json")
	public String listPetDetails(
			@ApiParam(allowableValues = "all,available,sold", defaultValue = "available", required=true, value="Inventory status to look for") 
			@PathVariable("status") String statuses) {
		return "Dog";
	}

	@ApiOperation(value = "Add a new pet to the store.", consumes="application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = String.class) })
	@PostMapping(value = "/", consumes="application/json")
	public String addPetDetails(@ApiParam(value="Pet details in JSON fromat", required=true) @RequestBody(required=true) String petDetails) {
		return "Dog";
	}

	@ApiOperation(value = "Remove a pet from the store.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pet removed", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Id Supplied", response = String.class),
			@ApiResponse(code = 404, message = "Pet Not Found", response = String.class) })
	@DeleteMapping(value = "/{petId}")
	public String removePetDetails(@ApiParam(required=true, value="Id of the pet to remove") @PathVariable("petId") String status) {
		return "Dog";
	}
}
