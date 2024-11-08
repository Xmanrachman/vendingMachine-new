package com.pos.test.vendingmachine.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pos.test.vendingmachine.model.dto.EntryProductsDTO;
import com.pos.test.vendingmachine.service.products.ServiceEntryProducts;

@RestController
public class EntryProductController {
	
	@Autowired
	private ServiceEntryProducts serviceEntryProd;
	
	@PostMapping("/entry/product")
	public ResponseEntity<Object> entryProduct(@RequestBody EntryProductsDTO request) {	
		return serviceEntryProd.entryProduct(request);
	}
}
