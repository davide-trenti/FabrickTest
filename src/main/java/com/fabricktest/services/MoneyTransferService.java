package com.fabricktest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fabricktest.config.ApiConnectionConfig;
import com.fabricktest.exceptions.MoneyTransferFailException;
import com.fabricktest.models.request.MoneyTransfer;
import com.fabricktest.models.response.ResponseHeader;

import utils.Utils;

/*
 * 
 * @author Davide Trenti
 */

@SuppressWarnings("rawtypes")
@Service
public class MoneyTransferService {

	private static Logger LOGGER = LoggerFactory.getLogger(MoneyTransferService.class);

	private ResponseEntity<ResponseHeader> response;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiConnectionConfig apiConnectionConfiguration;

	public ResponseEntity<ResponseHeader> performMoneyTransfer(String accountId, MoneyTransfer moneyTransfer)
			throws MoneyTransferFailException {

		LOGGER.info("Start MoneyTransferService - performMoneyTransfer");

		try {
			String uri = Utils.createUri(apiConnectionConfiguration.getAccountMoneyTransfer(), "https", apiConnectionConfiguration.getSourceAddress(), accountId, null);
			HttpEntity<MoneyTransfer> entity = new HttpEntity<MoneyTransfer>(moneyTransfer, Utils.createHeaders(apiConnectionConfiguration));
			response = restTemplate.exchange(uri, HttpMethod.POST, entity, ResponseHeader.class);
		} catch (HttpClientErrorException e) {
			throw new MoneyTransferFailException(e.getStatusCode().toString(), HttpStatus.BAD_REQUEST, e.getStatusText(), e.getResponseHeaders(), e.getResponseBodyAsByteArray(), null);
		}
		
		LOGGER.info("End MoneyTransferService - performMoneyTransfer");
		return response;
	}
}