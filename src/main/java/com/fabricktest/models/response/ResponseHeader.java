package com.fabricktest.models.response;

import java.util.List;

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
	private List<V> errors;
	private T payload;

}
