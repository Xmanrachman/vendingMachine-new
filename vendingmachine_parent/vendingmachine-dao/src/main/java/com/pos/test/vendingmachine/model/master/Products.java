package com.pos.test.vendingmachine.model.master;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "Products")
@Table(name ="products")
@AllArgsConstructor
@NoArgsConstructor
public class Products implements Serializable {

	private static final long serialVersionUID = 0l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Product")
	private Integer idProduct;
	
	@Column(name = "name_product")
	private String nameProduct;
	
	@Column(name = "code_product")
	private String codeProduct;
	
	@Column(name = "quantity_product")
	private Integer quantityProduct;
	
	@Column(name = "price_product")
	private Double priceProduct;
	
	
	
	
	
	
}
