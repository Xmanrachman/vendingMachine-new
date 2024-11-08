package com.pos.test.vendingmachine.model.trasnaction;

import java.io.Serializable;
import java.util.Date;

import com.pos.test.vendingmachine.model.master.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TransactionProductsEntity")
@Table(name = "transaction_product")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionProducts implements Serializable {

	private static final long serialVersionUID = 0l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction")
	private Integer idTranction;
	
	@Column(name = "code_transaction")
	private String codeTransaction;
	
	@Column(name = "date_transaction")
	private Date dateTransaction;
	
	@Column(name = "total_items")
	private Integer totalItems;
	
	@Column(name = "total_prices")
	private Double totalPrice;
	
	@Column(name = "total_payment")
	private Double totalPayment;
	
	@Column(name = "payment")
	private Double payment;
	
	@Column(name = "lembar_uang")
	private Integer lembarUang;
	
	@Column(name ="changes")
	private Double changes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_Product", referencedColumnName = "id_product")
	private Products itemProducts;
	
	
}
