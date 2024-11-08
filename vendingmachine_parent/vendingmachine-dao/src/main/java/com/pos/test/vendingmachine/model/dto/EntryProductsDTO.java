/**
 * 
 */
package com.pos.test.vendingmachine.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class EntryProductsDTO {

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
