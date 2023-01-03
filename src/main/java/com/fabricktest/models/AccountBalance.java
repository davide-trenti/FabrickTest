package com.fabricktest.models;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 
 * @author Davide Trenti
 */

public class AccountBalance {

	private Date date;
	private BigDecimal balance;
	private BigDecimal availableBalance;
	private String currency;

	public AccountBalance() {
		super();
	}
	
	public AccountBalance(Date date, BigDecimal balance, BigDecimal availableBalance, String currency) {
		super();
		this.date = date;
		this.balance = balance;
		this.availableBalance = availableBalance;
		this.currency = currency;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
