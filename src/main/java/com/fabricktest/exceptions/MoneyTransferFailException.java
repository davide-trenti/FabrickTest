package com.fabricktest.exceptions;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/*
 * 
 * @author Davide Trenti
 */

public class MoneyTransferFailException extends HttpClientErrorException {

	private static final long serialVersionUID = 1L;

	public MoneyTransferFailException(String message, HttpStatus statusCode, String statusText, HttpHeaders headers,
			byte[] body, Charset responseCharset) {
		super(message, statusCode, statusText, headers, body, responseCharset);
	}
}
