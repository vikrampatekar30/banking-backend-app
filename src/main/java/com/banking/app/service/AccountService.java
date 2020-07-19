package com.banking.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.dto.AccountDTO;
import com.banking.app.dto.AccountListDTO;
import com.banking.app.entity.Account;
import com.banking.app.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	public AccountListDTO listAccounts() {
		
		logger.debug("AccountService - listAccounts - start of method");
		
		AccountListDTO accountListDTO = new AccountListDTO();
		List<Account> accountList = accountRepository.findAll();
		accountListDTO.setResult(convertListEntityToDTO(accountList));
		
		logger.debug("AccountService - listAccounts - start of method");
		
		return accountListDTO;
	}
	
	public AccountDTO getAccount(int id) {
		
		logger.debug("AccountService - getAccount - start of method");
		
		AccountDTO accountDTO = null;
		Optional<Account> accountOptional = accountRepository.findById(id);
		if(accountOptional.isPresent()) {
			Account account = accountOptional.get();
			accountDTO = convertEntityToDTO(account);
		}
		
		logger.debug("AccountService - getAccount - start of method");
		
		return accountDTO;
	}
	
	public AccountDTO saveAccount(AccountDTO accountDTO) {
		
		logger.debug("AccountService - saveAccount - start of method");
		
		Account account = convertDTOToEntity(accountDTO);
		accountRepository.saveAndFlush(account);
		AccountDTO savedAccountDTO = convertEntityToDTO(account);
		
		logger.debug("AccountService - saveAccount - end of method");
		
		return savedAccountDTO;
	}
	
	public void deleteAccount(int id) {
		
		logger.debug("AccountService - deleteAccount - start of method");
		
		accountRepository.deleteById(id);
		
		logger.debug("AccountService - deleteAccount - end of method");
	}
	
	public AccountListDTO listCustomerAccounts(int customerId) {
		
		logger.debug("AccountService - listCustomerAccounts - start of method");
		
		AccountListDTO accountListDTO = new AccountListDTO();
		List<Account> accountList = accountRepository.findByCustomerId(customerId);
		accountListDTO.setResult(convertListEntityToDTO(accountList));
		
		logger.debug("AccountService - listCustomerAccounts - start of method");
		
		return accountListDTO;
	}
	
	private List<AccountDTO> convertListEntityToDTO(List<Account> accountList) {
		
		List<AccountDTO> accountDTOList = new ArrayList<>();
		accountList.stream().forEach(account -> {
			accountDTOList.add(convertEntityToDTO(account));
		});
		return accountDTOList;
	}
	
	private AccountDTO convertEntityToDTO(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setAccNo(account.getAccNo());
		accountDTO.setType(account.getType());
		accountDTO.setAmount(account.getAmount());
		accountDTO.setCustomerId(account.getCustomerId());
		accountDTO.setCurrency(account.getCurrency());
		return accountDTO;
	}
	
	private Account convertDTOToEntity(AccountDTO accountDTO) {
		Account account = new Account();
		account.setId(accountDTO.getId());
		account.setAccNo(accountDTO.getAccNo());
		account.setType(accountDTO.getType());
		account.setAmount(accountDTO.getAmount());
		account.setCustomerId(accountDTO.getCustomerId());
		account.setCurrency(accountDTO.getCurrency());
		return account;
	}
}
