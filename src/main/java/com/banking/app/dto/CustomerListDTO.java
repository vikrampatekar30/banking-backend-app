package com.banking.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerListDTO {

	@JsonProperty("result")
	private List<CustomerDTO> result;

	public List<CustomerDTO> getResult() {
		return result;
	}

	public void setResult(List<CustomerDTO> result) {
		this.result = result;
	}
}
