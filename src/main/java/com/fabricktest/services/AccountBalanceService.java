package com.fabricktest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fabricktest.config.ApiConnectionConfig;
import com.fabricktest.models.ResponseHeader;

import utils.Utils;

@SuppressWarnings("rawtypes")
@Service
public class AccountBalanceService {

	private static Logger LOGGER = LoggerFactory.getLogger(AccountBalanceService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiConnectionConfig apiConnectionConfiguration;

	private ResponseEntity<ResponseHeader> response;

	public ResponseEntity<ResponseHeader> getAccountBalance(String accountId) throws RestClientException {
		LOGGER.info("Start AccountService - getAccountBalance");

		HttpHeaders header = Utils.createHeaders(apiConnectionConfiguration);
		String uri = Utils.createUri(apiConnectionConfiguration.getAccountBalance(), "https",
				apiConnectionConfiguration.getSourceAddress(), accountId, null);
		response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(header), ResponseHeader.class);

		LOGGER.info("End AccountService - getAccountBalance");

		return response;
	}
}
