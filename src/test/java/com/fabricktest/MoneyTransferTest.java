package com.fabricktest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.fabricktest.models.request.MoneyTransfer;
import com.fabricktest.models.response.ResponseHeader;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("rawtypes")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public class MoneyTransferTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(MoneyTransferTest.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private String accountId = "14537780";
	private String sourceAddress = "http://127.0.0.1:8080/fabricktest-0.0.1-SNAPSHOT/";

	@Test
	public void performMoneyTransfer() throws IOException {
		LOGGER.info("Start MoneyTransferTest - performMoneyTransfer");

		InputStream requestBody = MoneyTransferTest.class.getResourceAsStream("/static/moneyTransferRequest.json");
		String requestBodyS = "";

		requestBodyS = new String(requestBody.readAllBytes(), Charset.defaultCharset());

		String uri = sourceAddress + "/{accountId}/moneyTransfer";

		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		MoneyTransfer mt = new MoneyTransfer();

		mt = objectMapper.readValue(requestBodyS, MoneyTransfer.class);

		ResponseEntity<ResponseHeader> accountServiceResponseHeader = this.restTemplate.exchange(uri,
				HttpMethod.POST, new HttpEntity<>(mt, header), ResponseHeader.class, accountId);

		assertTrue(accountServiceResponseHeader.getBody().getErrors().toString().contains("API000"));
		LOGGER.info("End MoneyTransferTest - performMoneyTransfer");
	}

	@Test
	public void performMoneyTransferWrongAccountId() throws IOException {
		LOGGER.info("Start MoneyTransferTest - performMoneyTransfer");
		accountId = "000";

		InputStream requestBody = MoneyTransferTest.class.getResourceAsStream("/static/moneyTransferRequest.json");
		String requestBodyS = "";

		requestBodyS = new String(requestBody.readAllBytes(), Charset.defaultCharset());

		String uri = sourceAddress + "/{accountId}/moneyTransfer";

		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		MoneyTransfer mt = new MoneyTransfer();

		mt = objectMapper.readValue(requestBodyS, MoneyTransfer.class);

		ResponseEntity<ResponseHeader> accountServiceResponseHeader = this.restTemplate.exchange(uri,
				HttpMethod.POST, new HttpEntity<>(mt, header), ResponseHeader.class, accountId);

		assertTrue(accountServiceResponseHeader.getBody().getErrors().toString().contains("Invalid account identifier"));
		LOGGER.info("End MoneyTransferTest - performMoneyTransfer");
	}
}