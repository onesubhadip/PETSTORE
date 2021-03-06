package org.petstore.util;


import org.petstore.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
/**
*	Global exception handler class for spring MVC
*/
@ControllerAdvice
@RestController
public class AppExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		FieldError error = result.getFieldError();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(error.getDefaultMessage()));
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class, JsonMappingException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> invalidInputFormat() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Input can not be parsed."));
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Request could not be completed"));
	}
}
