package org.petstore.exception;

/*
 * Wrapper exception to be thrown from service class
 */
public class PetServiceExcption extends RuntimeException{

	public PetServiceExcption(String message) {
		super(message);
	}

	public PetServiceExcption(Throwable cause) {
		super(cause);
	}
	
}
