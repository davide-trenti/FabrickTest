package com.fabricktest.models.request;

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
	
	private String accountCode;
	private String bicCode;

}
