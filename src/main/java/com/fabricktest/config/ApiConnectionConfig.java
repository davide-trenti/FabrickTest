package com.fabricktest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import lombok.Setter;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

/*
 * 
 * @author Davide Trenti
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PropertySource(value={"classpath:application.properties"})
public class ApiConnectionConfig {

	@Value("${sourceAddress}")
	String sourceAddress;
	@Value("${keyValue}")
	String keyValue;
	@Value("${authSchemaValue}")
	String authSchemaValue;
	@Value("${uri.accountBalance}")
	String accountBalance;
	@Value("${uri.accountTransactionList}")
	String accountTransactionList;
	@Value("${uri.accountMoneyTransfer}")
	String accountMoneyTransfer;

}