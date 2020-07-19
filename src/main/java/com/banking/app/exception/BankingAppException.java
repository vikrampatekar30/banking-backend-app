package com.banking.app.exception;

public class BankingAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BankingAppException(String message) {
		super(message);
	}
}
