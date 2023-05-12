package com.fit.invest.invest.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fit.invest.invest.domain.Account;
import com.fit.invest.invest.enums.TransactionType;

@RestController
@RequestMapping(value = "invest")
public class InvestController {

	private Account account = new Account();

	RestTemplate restTemplate = new RestTemplate();

	@PostMapping(value = "/deposit")
	public ResponseEntity<BigDecimal> deposit(@RequestBody String value) {
		var depositValue = new BigDecimal(value);
		var diff = depositValue.subtract(BigDecimal.valueOf(5000));

		if (diff.compareTo(BigDecimal.ZERO) >= 0) {
			ResponseEntity<BigDecimal> result = restTemplate.postForEntity("http://localhost:8080/account/deposit",
					value, BigDecimal.class);
			return result;

		} else {
			TransactionType.DEPOSIT.doTransactionOperation(account, new BigDecimal(value));
			return new ResponseEntity<>(account.getBalance(), HttpStatus.OK);
		}

	}
}
