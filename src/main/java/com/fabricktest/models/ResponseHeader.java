package com.fabricktest.models;

import java.util.List;

public class ResponseHeader<T> {
	
	private String status;
	private List<String> error;
	private T payload;
	
	public ResponseHeader() {
		super();
	}
	
	public ResponseHeader(String status, List<String> error, T payload) {
		super();
		this.status = status;
		this.error = error;
		this.payload = payload;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getError() {
		return error;
	}
	public void setError(List<String> error) {
		this.error = error;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
}
