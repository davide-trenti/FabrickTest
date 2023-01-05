package com.fabricktest.models.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/*
 * 
 * @author Davide Trenti
 */

@Getter
@Setter
public class ErrorResponse {

	private HttpStatus httpStatus;
	private String timestamp;
	private String message;

	public ErrorResponse() {
		this.timestamp = LocalDateTime.now().toString();
	}

	public ErrorResponse(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public ErrorResponse(HttpStatus httpStatus, String message) {
		this();
		this.httpStatus = httpStatus;
		this.message = message;
	}
}