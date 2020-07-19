package com.banking.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.banking.app.api.FundTransferApi;
import com.banking.app.dto.FundTransferDTO;
import com.banking.app.exception.BankingAppException;
import com.banking.app.service.FundTransferService;

@Controller
public class FundTransferApiController implements FundTransferApi {

	private HttpServletRequest request;
	private static final Logger logger = LoggerFactory.getLogger(FundTransferApiController.class);
	
	@Autowired
	private FundTransferService fundTransferService;
	
	@Autowired
	public FundTransferApiController(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public ResponseEntity<FundTransferDTO> transfer(FundTransferDTO fundTransferDTO) {
		logger.debug("FundTransferApiController - transfer - start of method");
		
		proceedIfAcceptHeaderMatch();
		FundTransferDTO result = fundTransferService.transfer(fundTransferDTO);
		
		logger.debug("FundTransferApiController - transfer - end of method");
		
		if(result != null) {
			return new ResponseEntity<FundTransferDTO>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<FundTransferDTO>(new FundTransferDTO(), HttpStatus.OK);
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
