package com.banking.app.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.dto.FundTransferDTO;
import com.banking.app.entity.Account;
import com.banking.app.repository.AccountRepository;

@Service
public class FundTransferService {

private static final Logger logger = LoggerFactory.getLogger(FundTransferService.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	public FundTransferDTO transfer(FundTransferDTO fundTransferDTO) {
		
		logger.debug("FundTransferService - transfer - start of method");
		
		Optional<Account> fromAccountOptional = accountRepository.findById(fundTransferDTO.getFromAccountId());
		if(fromAccountOptional.isPresent()) {
			Account fromAccount = fromAccountOptional.get();
			if(fundTransferDTO.getAmount() > fromAccount.getAmount()) {
				fundTransferDTO.setMessage("Fund transfer failed due to insufficient balance.");
				fundTransferDTO.setError(true);
			} else {
				Optional<Account> toAccountOptional = accountRepository.findById(fundTransferDTO.getToAccountId());
				if(toAccountOptional.isPresent()) {
					Account toAccount = toAccountOptional.get();
					toAccount.setAmount(toAccount.getAmount() + fundTransferDTO.getAmount());
					fromAccount.setAmount(fromAccount.getAmount() - fundTransferDTO.getAmount());
					accountRepository.saveAndFlush(toAccount);
					accountRepository.saveAndFlush(fromAccount);
					fundTransferDTO.setMessage("Fund transferred successfully.");
					fundTransferDTO.setError(false);
				} else {
					fundTransferDTO.setMessage("To account doesn't exists.");
					fundTransferDTO.setError(true);
				}
				
			}
		} else {
			fundTransferDTO.setMessage("From account doesn't exists.");
			fundTransferDTO.setError(true);
		}
		
		logger.debug("FundTransferService - transfer - start of method");
		
		return fundTransferDTO;
	}
}
