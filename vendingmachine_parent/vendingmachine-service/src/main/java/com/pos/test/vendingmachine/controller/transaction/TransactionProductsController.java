package com.pos.test.vendingmachine.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pos.test.vendingmachine.model.dto.TransactionDTO;
import com.pos.test.vendingmachine.service.transaction.ServiceTransactionProduct;

@RestController
public class TransactionProductsController {
	
	@Autowired
	private ServiceTransactionProduct serviceTransactionProd;
	
	@PostMapping("/transaction/trx-products")
	public ResponseEntity<Object> transactionProducts(@RequestBody TransactionDTO transactionRequest) {
		return serviceTransactionProd.transactionProducts(transactionRequest);
	}

}
