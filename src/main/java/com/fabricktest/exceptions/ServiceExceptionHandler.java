package com.fabricktest.exceptions;

import java.io.IOException;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fabricktest.models.response.CustomError;
import com.fabricktest.models.response.ErrorResponse;
import com.fabricktest.models.response.ResponseHeader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 
 * @author Davide Trenti
 */

@SuppressWarnings("unchecked")
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ResponseHeader<CustomError, String>> handleInvalidAccountId(HttpClientErrorException e) throws JsonMappingException, JsonProcessingException {

		ResponseHeader<CustomError, String> responseHeader = objectMapper.readValue(e.getResponseBodyAsString(), ResponseHeader.class);
		return buildResponseEntity(responseHeader);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleWrongParams(ConstraintViolationException e) {
		String error = "";
		if (e.getMessage() != null) {
			error = e.getMessage();
		} else {
			error = "Input params validation failed. Account identifier must be a number. Date format: yyyy-MM-dd.";
		}

		return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String error = "Missing param(s).";
		ErrorResponse errorDetails = new ErrorResponse(HttpStatus.BAD_REQUEST, error);
		return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getHttpStatus(), request);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ErrorResponse> handleJsonParseError(IOException e) {

		String error = "Error building MoneyTransfer from Json.";
		return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MoneyTransferFailException.class)
	public ResponseEntity<ResponseHeader<CustomError, String>> handleMoneyTransferFail(MoneyTransferFailException e) throws JsonMappingException, JsonProcessingException {

		ResponseHeader<CustomError, String> responseHeader = objectMapper.readValue(e.getResponseBodyAsString(), ResponseHeader.class);
		return buildResponseEntity(responseHeader);
	}

	private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorResponse errorResponse) {
		return new ResponseEntity<ErrorResponse>(errorResponse, errorResponse.getHttpStatus());
	}

	private ResponseEntity<ResponseHeader<CustomError, String>> buildResponseEntity(ResponseHeader<CustomError, String> responseHeader) {
		return new ResponseEntity<ResponseHeader<CustomError, String>>(responseHeader, HttpStatus.BAD_REQUEST);
	}
}