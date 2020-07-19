package com.banking.app.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banking.app.dto.FundTransferDTO;

@RequestMapping(value = "/fundtransfer")
public interface FundTransferApi {
	
	@RequestMapping(value = "/transfer", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	ResponseEntity<FundTransferDTO> transfer(@RequestBody FundTransferDTO fundTransferDTO);
}
