package com.pos.test.vendingmachine.service.products;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pos.test.vendingmachine.model.dto.EntryProductsDTO;
import com.pos.test.vendingmachine.model.master.Products;
import com.pos.test.vendingmachine.repository.ProductRepository;
import com.pos.test.vendingmachine.response.GeneralResponse;

@Service
public class ServiceEntityProducts {

	@Autowired
	private ProductRepository productRepo;

	public ResponseEntity<Object> entryProduct(EntryProductsDTO requestEntryProduct) {

		GeneralResponse<Object> response = new GeneralResponse<>();
		Products productEntity = new Products();

		if (requestEntryProduct.equals(null) || requestEntryProduct.getIdProduct() != 0) {

			response.setInfo("data entry failed, data is empty");
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			response.setEntity(productEntity);
			return ResponseEntity.status(response.getStatus()).body(response);
		}

		productEntity.setCodeProduct(requestEntryProduct.getCodeProduct());
		productEntity.setNameProduct(requestEntryProduct.getNameProduct());
		productEntity.setPriceProduct(requestEntryProduct.getPriceProduct());
		productEntity.setQuantityProduct(requestEntryProduct.getQuantityProduct());

		productRepo.saveAndFlush(productEntity);

		if (productEntity.getIdProduct() != 0l) {

			response.setInfo("Data product is succesfuly");
			response.setStatus(HttpStatus.SC_OK);
			response.setEntity(productEntity);
		}

		return ResponseEntity.status(response.getStatus()).body(response);

	}
}
