package com.fabricktest.models;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 
 * @author Davide Trenti
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountBalance {

	private Date date;
	private BigDecimal balance;
	private BigDecimal availableBalance;
	private String currency;

}
