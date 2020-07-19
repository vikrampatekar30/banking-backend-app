package com.banking.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountListDTO {

	@JsonProperty("result")
	private List<AccountDTO> result;

	public List<AccountDTO> getResult() {
		return result;
	}

	public void setResult(List<AccountDTO> result) {
		this.result = result;
	}
}
