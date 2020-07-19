package com.banking.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.banking.app.api.AccountApi;
import com.banking.app.dto.AccountDTO;
import com.banking.app.dto.AccountListDTO;
import com.banking.app.exception.BankingAppException;
import com.banking.app.service.AccountService;

@Controller
public class AccountApiController implements AccountApi {

	private HttpServletRequest request;
	private static final Logger logger = LoggerFactory.getLogger(AccountApiController.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	public AccountApiController(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public ResponseEntity<AccountListDTO> listAccounts() {
		
		logger.debug("AccountApiController - listAccounts - start of method");
		
		proceedIfAcceptHeaderMatch();
		AccountListDTO accountListDTO = accountService.listAccounts();
		
		logger.debug("AccountApiController - listAccounts - end of method");
		
		if(accountListDTO.getResult() != null && accountListDTO.getResult().size() > 0) {
			return new ResponseEntity<AccountListDTO>(accountListDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<AccountListDTO>(new AccountListDTO(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<AccountDTO> getAccount(int id) {
		
		logger.debug("AccountApiController - getAccount - start of method");
		
		proceedIfAcceptHeaderMatch();
		AccountDTO accountDTO = accountService.getAccount(id);
		
		logger.debug("AccountApiController - getAccount - end of method");
		
		if(accountDTO != null) {
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<AccountDTO>(new AccountDTO(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<AccountDTO> saveAccount(AccountDTO accountDTO) {

		logger.debug("AccountApiController - saveAccount - start of method");
		
		proceedIfAcceptHeaderMatch();
		AccountDTO savedAccountDTO = accountService.saveAccount(accountDTO);
		
		logger.debug("AccountApiController - saveAccount - end of method");
		if(accountDTO != null) {
			return new ResponseEntity<AccountDTO>(savedAccountDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<AccountDTO>(new AccountDTO(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Void> deleteAccount(int id) {
		
		logger.debug("AccountApiController - deleteAccount - start of method");
		
		accountService.deleteAccount(id);
		
		logger.debug("AccountApiController - deleteAccount - end of method");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AccountListDTO> listCustomerAccounts(int customerId) {
		
		logger.debug("AccountApiController - listCustomerAccounts - start of method");
		
		proceedIfAcceptHeaderMatch();
		AccountListDTO accountListDTO = accountService.listCustomerAccounts(customerId);
		
		logger.debug("AccountApiController - listCustomerAccounts - end of method");
		
		if(accountListDTO.getResult() != null && accountListDTO.getResult().size() > 0) {
			return new ResponseEntity<AccountListDTO>(accountListDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<AccountListDTO>(new AccountListDTO(), HttpStatus.OK);
		}
	}
	
	private void proceedIfAcceptHeaderMatch() {
		String accept = request.getHeader("Accept");
		
		if(accept != null && !accept.contains("application/json")) {
			BankingAppException exception = new BankingAppException("API response type is different than what client is expecting");
			logger.error(exception.getMessage());
			throw exception;
		}
	}
}