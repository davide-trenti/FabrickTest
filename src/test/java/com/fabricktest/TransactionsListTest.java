package com.fabricktest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.fabricktest.models.response.ErrorResponse;
import com.fabricktest.models.response.ResponseHeader;

@SuppressWarnings("rawtypes")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public class TransactionsListTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsListTest.class);

	@Autowired
	private TestRestTemplate restTemplate;
	
	private String sourceAddress = "http://127.0.0.1:8080/fabricktest-0.0.1-SNAPSHOT/";
	private String accountId = "14537780";
	private String fromAccountingDate = "2022-01-01";
    private String toAccountingDate = "2022-04-01";
	
	@Test
	public void getTransactionsList() {
		LOGGER.info("Start TransactionsListTests - getTransactionsList");

		String uriVariables = "?fromAccountingDate="+fromAccountingDate+"&toAccountingDate="+toAccountingDate;
		String uri = sourceAddress + "/{accountId}/transactions"+uriVariables;

		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<ResponseHeader> accountServiceResponseHeader = this.restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(header), ResponseHeader.class, accountId);
		
		assertEquals(HttpStatus.OK, accountServiceResponseHeader.getStatusCode());
		assertTrue(accountServiceResponseHeader.getBody().getPayload().toString().contains("transactionId"));
		LOGGER.info("End TransactionsListTests - getTransactionsList");
	}
	
	@Test
	public void getTransactionsListWrongAccountId() {
		LOGGER.info("Start TransactionsListTests - getTransactionsList");
		
		accountId = "000";
		
		String uriVariables = "?fromAccountingDate="+fromAccountingDate+"&toAccountingDate="+toAccountingDate;
		String uri = sourceAddress + "/{accountId}/transactions"+uriVariables;

		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<ResponseHeader> response = this.restTemplate.exchange(uri,
				HttpMethod.GET, new HttpEntity<>(header), ResponseHeader.class, accountId);
		
		assertTrue(response.getBody().getErrors().toString().contains("Invalid account identifier"));
		LOGGER.info("End TransactionsListTests - getTransactionsList");
	}
	
	@Test
	public void getAccountBalanceWrongDateFormat() {
		LOGGER.info("Start FabtestApplicationTests - getAccountBalanceNonNumericAccountId");

		fromAccountingDate = "wrong-date-format";

		String uriVariables = "?fromAccountingDate="+fromAccountingDate+"&toAccountingDate="+toAccountingDate;
		String uri = sourceAddress + "/{accountId}/transactions"+uriVariables;

		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		ResponseEntity<ErrorResponse> response = this.restTemplate.exchange(uri,
				HttpMethod.GET, new HttpEntity<>(header), ErrorResponse.class, accountId);

		assertTrue(response.getBody().getMessage().toString().contains("Wrong date format."));
		LOGGER.info("End FabtestApplicationTests - getAccountBalanceNonNumericAccountId");
	}
}