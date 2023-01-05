package com.fabricktest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fabricktest.config.ApiConnectionConfig;
import com.fabricktest.models.response.ResponseHeader;

import utils.Utils;

/*
 * 
 * @author Davide Trenti
 */

@SuppressWarnings("rawtypes")
@Service
public class TransactionsListService {

	private static Logger LOGGER = LoggerFactory.getLogger(TransactionsListService.class);

	private ResponseEntity<ResponseHeader> response;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiConnectionConfig apiConnectionConfiguration;

	public ResponseEntity<ResponseHeader> getTransactionList(String accountId, MultiValueMap<String, String> params)
			throws RestClientException {
		LOGGER.info("Start TransactionListService - getTransactionList");

		HttpHeaders header = Utils.createHeaders(apiConnectionConfiguration);
		String uri = Utils.createUri(apiConnectionConfiguration.getAccountTransactionList(), "https", apiConnectionConfiguration.getSourceAddress(), accountId, params);
		response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(header), ResponseHeader.class);

		LOGGER.info("End TransactionListService - getTransactionList");
		return response;
	}
}