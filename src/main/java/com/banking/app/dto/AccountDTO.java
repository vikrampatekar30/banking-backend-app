package com.banking.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDTO {
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("accNo")
	private long accNo;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("amount")
	private int amount;
	
	@JsonProperty("customerId")
	private int customerId;
	
	@JsonProperty("currency")
	private String currency;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
}
