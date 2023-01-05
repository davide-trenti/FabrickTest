package com.fabricktest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * 
 * @author Davide Trenti
 */

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAccountId(RestClientException e) {

		String error = "Invalid account identifier.";
		return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
	}

	private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorResponse errorResponse) {
		return new ResponseEntity<ErrorResponse>(errorResponse, errorResponse.getHttpStatus());
	}
}