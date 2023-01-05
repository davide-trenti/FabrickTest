package com.fabricktest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 
 * @author Davide Trenti
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

	private Long accountId;
	private String iban;
	private String abiCode;
	private String cabCode;
	private String countryCode;
	private String internationalCin;
	private String nationalCin;
	private String account;
	private String alias;
	private String productName;
	private String holderName;
	private String activatedDate;
	private String currency;

}
