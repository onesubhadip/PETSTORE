package org.petstore.exception;

public class PetServiceExcption extends RuntimeException{

	public PetServiceExcption(String message) {
		super(message);
	}

	public PetServiceExcption(Throwable cause) {
		super(cause);
	}
	
}
