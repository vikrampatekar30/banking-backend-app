package com.banking.app.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.banking.app.dto.CustomerDTO;
import com.banking.app.dto.CustomerListDTO;

@RequestMapping(value = "/customer")
public interface CustomerApi {

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity<CustomerListDTO> listCustomers();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity<CustomerDTO> getCustomer(@PathVariable int id);
	
	@RequestMapping(value = "/save", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteCustomer(@PathVariable int id);
}
