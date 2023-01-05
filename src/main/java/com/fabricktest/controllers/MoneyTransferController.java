package com.fabricktest.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fabricktest.exceptions.MoneyTransferFailException;
import com.fabricktest.models.request.MoneyTransfer;
import com.fabricktest.models.response.ResponseHeader;
import com.fabricktest.services.MoneyTransferService;

/*
 * 
 * @author Davide Trenti
 */

@Controller
@Validated
public class MoneyTransferController {

	@Autowired
	private MoneyTransferService moneyTransferService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/{accountId}/moneyTransfer")
	public ResponseEntity<ResponseHeader> performMoneyTransfer(
			@PathVariable(value = "accountId") @Pattern(regexp = "^[0-9]*$", message = "Account identifier must be a number.") String accountId,
			@RequestBody @Valid MoneyTransfer moneyTransfer, BindingResult bindingResult) throws MoneyTransferFailException {

		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException(bindingResult.getAllErrors().toString());
		}

		return moneyTransferService.performMoneyTransfer(accountId, moneyTransfer);
	}
}