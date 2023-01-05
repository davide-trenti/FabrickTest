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
public class ResponseHeader<V, T> {

	private String status;
	private V errors;
	private T payload;

}
