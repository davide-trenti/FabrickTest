package com.fabricktest.models.request;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class MoneyTransfer {

	private String accountId;
	@NotNull
	private Creditor creditor;
	@NotNull
	private String description;
	@NotNull
	private BigDecimal amount;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date executionDate;
	private String uri;
	private String currency;
	private String isUrgent;
	private String isInstant;
	private String feeType;
	private String feeAccountId;

}