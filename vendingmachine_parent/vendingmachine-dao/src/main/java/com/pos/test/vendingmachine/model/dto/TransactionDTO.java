package com.pos.test.vendingmachine.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {
	
	@JsonProperty("id_transaction")
	private Integer idTranction;
	
	@JsonProperty("code_transaction")
	private String codeTransaction;
	
	@JsonProperty("date_transaction")
	private String dateTransaction;
	
	@JsonProperty("total_items")
	private Integer totalItems;
	
	@JsonProperty("total_prices")
	private Double totalPrice;
	
	@JsonProperty("total_payment")
	private Double totalPayment;
	
	@JsonProperty("payment")
	private Double payment;
	
	@JsonProperty("change")
	private Double change;
	
	@JsonProperty("payment_methode")
	private List<PaymentMetode> paymentMetode;

	@JsonProperty("data_products")
	private DataProducts dataProducts;
	
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PaymentMetode {
		
		@JsonProperty("money_payment")
		private Double moneyPayment;
		
		@JsonProperty("lembar_uang")
		private Integer lembarUang;
	}
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class DataProducts {
		@JsonProperty("id_product")
		private Integer idProduct;
		
		@JsonProperty("name_product")
		private String nameProduct;
		
		@JsonProperty("code_product")
		private String codeProduct;
		
		@JsonProperty("quantity_product")
		private Integer quantityProduct;
		
		@JsonProperty("price_product")
		private Double priceProduct;
	}


	
}

