package com.fabricktest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/*
 * 
 * @author Davide Trenti
 */

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
	
	public ApiConnectionConfig() {
		super();
	}
	
	public ApiConnectionConfig(String sourceAddress, String keyValue, String authSchemaValue,
			String accountBalance, String accountTransactionList, String accountMoneyTransfer) {
		super();
		this.sourceAddress = sourceAddress;
		this.keyValue = keyValue;
		this.authSchemaValue = authSchemaValue;
		this.accountBalance = accountBalance;
		this.accountTransactionList = accountTransactionList;
		this.accountMoneyTransfer = accountMoneyTransfer;
	}
	
	public String getSourceAddress() {
		return sourceAddress;
	}
	
	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	
	public String getKeyValue() {
		return keyValue;
	}
	
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
	public String getAuthSchemaValue() {
		return authSchemaValue;
	}
	
	public void setAuthSchemaValue(String authSchemaValue) {
		this.authSchemaValue = authSchemaValue;
	}
	
	public String getAccountBalance() {
		return accountBalance;
	}
	
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public String getAccountTransactionList() {
		return accountTransactionList;
	}
	
	public void setAccountTransactionList(String accountTransactionList) {
		this.accountTransactionList = accountTransactionList;
	}
	
	public String getAccountMoneyTransfer() {
		return accountMoneyTransfer;
	}
	
	public void setAccountMoneyTransfer(String accountMoneyTransfer) {
		this.accountMoneyTransfer = accountMoneyTransfer;
	}
}