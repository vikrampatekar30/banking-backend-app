package com.banking.app.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.banking.app.api.CustomerApi;
import com.banking.app.dto.CustomerDTO;
import com.banking.app.dto.CustomerListDTO;
import com.banking.app.exception.BankingAppException;
import com.banking.app.service.CustomerService;

@Controller
public class CustomerApiController implements CustomerApi {

	private HttpServletRequest request;
	private static final Logger logger = LoggerFactory.getLogger(CustomerApiController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	public CustomerApiController(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public ResponseEntity<CustomerListDTO> listCustomers() {
		
		logger.debug("CustomerApiController - listCustomers - start of method");
		
		proceedIfAcceptHeaderMatch();
		CustomerListDTO customerListDTO = customerService.listCustomers();
		
		logger.debug("CustomerApiController - listCustomers - end of method");

		if(customerListDTO.getResult() != null && customerListDTO.getResult().size() > 0) {
			return new ResponseEntity<CustomerListDTO>(customerListDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<CustomerDTO> getCustomer(int id) {
		
		logger.debug("CustomerApiController - getCustomer - start of method");
		
		proceedIfAcceptHeaderMatch();
		CustomerDTO customerDTO = customerService.getCustomer(id);
		
		logger.debug("CustomerApiController - getCustomer - end of method");
		
		if(customerDTO != null) {
			return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<CustomerDTO>(new CustomerDTO(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<CustomerDTO> saveCustomer(CustomerDTO customerDTO) {

		logger.debug("CustomerApiController - saveCustomer - start of method");
		
		proceedIfAcceptHeaderMatch();
		CustomerDTO savedCustomerDTO = customerService.saveCustomer(customerDTO);
		
		logger.debug("CustomerApiController - saveCustomer - end of method");
		
		if(customerDTO != null) {
			return new ResponseEntity<CustomerDTO>(savedCustomerDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<CustomerDTO>(new CustomerDTO(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Void> deleteCustomer(int id) {

		logger.debug("CustomerApiController - deleteCustomer - start of method");
		
		customerService.deleteCustomer(id);
		
		logger.debug("CustomerApiController - deleteCustomer - end of method");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
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
