package com.banking.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.dto.CustomerDTO;
import com.banking.app.dto.CustomerListDTO;
import com.banking.app.entity.Customer;
import com.banking.app.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	public CustomerListDTO listCustomers() {
		
		logger.debug("CustomerService - listCustomers - start of method");
		
		CustomerListDTO customerListDTO = new CustomerListDTO();
		List<Customer> customerList = customerRepository.findAll();
		customerListDTO.setResult(convertListEntityToDTO(customerList));
		
		logger.debug("CustomerService - listCustomers - start of method");
		
		return customerListDTO;
	}
	
	public CustomerDTO getCustomer(int id) {
		
		logger.debug("CustomerService - getCustomer - start of method");
		
		CustomerDTO customerDTO = null;
		Optional<Customer> customerOptional = customerRepository.findById(id);
		if(customerOptional.isPresent()) {
			Customer customer = customerOptional.get();
			customerDTO = convertEntityToDTO(customer);
		}
		
		logger.debug("CustomerService - getCustomer - start of method");
		
		return customerDTO;
	}
	
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		
		logger.debug("CustomerService - saveCustomer - start of method");
		
		Customer customer = convertDTOToEntity(customerDTO);
		customer = customerRepository.saveAndFlush(customer);
		CustomerDTO savedCustomerDTO = convertEntityToDTO(customer);
		
		logger.debug("CustomerService - saveCustomer - end of method");
		
		return savedCustomerDTO;
	}
	
	public void deleteCustomer(int id) {
		
		logger.debug("CustomerService - deleteCustomer - start of method");
		
		customerRepository.deleteById(id);
		
		logger.debug("CustomerService - deleteCustomer - end of method");
	}
	
	private List<CustomerDTO> convertListEntityToDTO(List<Customer> customerList) {
		
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		customerList.stream().forEach(customer -> {
			customerDTOList.add(convertEntityToDTO(customer));
		});
		return customerDTOList;
	}
	
	private CustomerDTO convertEntityToDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setPhoneNumber(customer.getPhoneNumber());
		customerDTO.setSsn(customer.getSsn());
		return customerDTO;
	}
	
	private Customer convertDTOToEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setId(customerDTO.getId());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setAddress(customerDTO.getAddress());
		customer.setPhoneNumber(customerDTO.getPhoneNumber());
		customer.setSsn(customerDTO.getSsn());
		return customer;
	}
}