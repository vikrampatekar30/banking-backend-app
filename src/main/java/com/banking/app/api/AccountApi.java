package com.banking.app.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banking.app.dto.AccountDTO;
import com.banking.app.dto.AccountListDTO;

@RequestMapping(value = "/account")
public interface AccountApi {

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity<AccountListDTO> listAccounts();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity<AccountDTO> getAccount(@PathVariable int id);
	
	@RequestMapping(value = "/save", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO accountDTO);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteAccount(@PathVariable int id);
	
	@RequestMapping(value = "/listCustomerAccounts/{customerId}", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity<AccountListDTO> listCustomerAccounts(@PathVariable int customerId);
}
