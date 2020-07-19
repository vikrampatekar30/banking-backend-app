package com.banking.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FundTransferDTO {

	@JsonProperty("customerId")
	private int customerId;
	
	@JsonProperty("fromAccountId")
	private int fromAccountId;
	
	@JsonProperty("toAccountId")
	private int toAccountId;
	
	@JsonProperty("amount")
	private int amount;
	
	@JsonProperty("isError")
	private boolean isError;
	
	@JsonProperty("message")
	private String message;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public int getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}