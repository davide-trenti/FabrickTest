package com.fabricktest.controllers;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fabricktest.models.response.ResponseHeader;
import com.fabricktest.services.AccountBalanceService;

/*
 * 
 * @author Davide Trenti
 */

@RestController
@Validated
public class AccountBalanceController {

	@Autowired
	private AccountBalanceService accountBalanceService;

	@SuppressWarnings("rawtypes")
	@GetMapping("/{accountId}/balance")
	public ResponseEntity<ResponseHeader> getBalance(
			@PathVariable("accountId") @Pattern(regexp = "^[0-9]*$", message = "Account identifier must be a number.") String accountId) {

		return accountBalanceService.getAccountBalance(accountId);
	}
}
