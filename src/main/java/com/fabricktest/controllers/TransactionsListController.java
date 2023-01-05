package com.fabricktest.controllers;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabricktest.models.response.ResponseHeader;
import com.fabricktest.services.TransactionsListService;

/*
 * 
 * @author Davide Trenti
 */

@RestController
@Validated
public class TransactionsListController {

	@Autowired
	private TransactionsListService transactionListService;

	@SuppressWarnings("rawtypes")
	@GetMapping("/{accountId}/transactions")
	public ResponseEntity<ResponseHeader> getTransactionList(
			@PathVariable(value = "accountId") @Pattern(regexp = "^[0-9]*$", message = "Account identifier must be a number.") String accountId,
			@RequestParam(value = "fromAccountingDate") @Pattern(regexp = "[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}", message = "Wrong date format. Must be: yyyy-MM-dd") String fromAccountingDate,
			@RequestParam(value = "toAccountingDate") @Pattern(regexp = "[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}", message = "Wrong date format. Must be: yyyy-MM-dd") String toAccountingDate) {

		MultiValueMap<String, String> params = new HttpHeaders();
		params.add("fromAccountingDate", fromAccountingDate);
		params.add("toAccountingDate", toAccountingDate);

		return transactionListService.getTransactionList(accountId, params);
	}
}