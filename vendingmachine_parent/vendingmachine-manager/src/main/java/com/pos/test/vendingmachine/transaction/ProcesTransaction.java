package com.pos.test.vendingmachine.transaction;

import org.springframework.stereotype.Component;

import com.pos.test.vendingmachine.model.dto.TransactionDTO;
import com.pos.test.vendingmachine.model.dto.TransactionDTO.PaymentMetode;
import com.pos.test.vendingmachine.model.master.Products;
import com.pos.test.vendingmachine.process.ProductsResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProcesTransaction {

	public Products processValidationQty(Integer requestProd, Products product) {
		Integer remainsQty = product.getQuantityProduct() - requestProd;
		product.setQuantityProduct(remainsQty);
		return product;
	}

	public ProductsResponse processValidationPayment(TransactionDTO request) {
		boolean checkingPayment = true;
		ProductsResponse responseInquery = new ProductsResponse();

		if (request.getPaymentMetode().size() <= 0) {
			checkingPayment = false;
			responseInquery.setCheckingPayment(checkingPayment);
			return responseInquery;
		}
		int calLembarUang = 0;
		double calPayment = 0;
		double remains = 0;
		for (PaymentMetode loopPaymentMetode : request.getPaymentMetode()) {

			if (loopPaymentMetode.getMoneyPayment() == 2000 || loopPaymentMetode.getMoneyPayment() == 5000
					|| loopPaymentMetode.getMoneyPayment() == 10000 || loopPaymentMetode.getMoneyPayment() == 20000
					|| loopPaymentMetode.getMoneyPayment() == 50000) {

				checkingPayment = true;
				responseInquery.setCheckingPayment(checkingPayment);

			} else {
				checkingPayment = false;
				responseInquery.setCheckingPayment(checkingPayment);
				return responseInquery;
			}
			log.info("checking calLembarUang "+loopPaymentMetode.getLembarUang() +
					" Payment"+loopPaymentMetode.getMoneyPayment());
			calLembarUang = loopPaymentMetode.getLembarUang() + calLembarUang;
			calPayment = loopPaymentMetode.getMoneyPayment() + calPayment;
		}

		log.info("Checking calLembarUang :"+calLembarUang +" "+calPayment);
		if (request.getDataProducts().getNameProduct().equalsIgnoreCase("Biskuit")) {

			responseInquery = metodePayment(calLembarUang, calPayment, checkingPayment, remains, request);

		} else if (request.getDataProducts().getNameProduct().equalsIgnoreCase("Chips")) {

			responseInquery = metodePayment(calLembarUang, calPayment, checkingPayment, remains, request);

		} else if (request.getDataProducts().getNameProduct().equalsIgnoreCase("Oreo") ) {

			responseInquery = metodePayment(calLembarUang, calPayment, checkingPayment, remains, request);
			log.info("println response :"+responseInquery);
		} else if (request.getDataProducts().getNameProduct().equalsIgnoreCase("Tango")) {

			if (calLembarUang == 2) {
				if (calPayment == 12000) {
					checkingPayment = true;
				} else {
					checkingPayment = false;
					responseInquery.setCheckingPayment(checkingPayment);
					return responseInquery;
				}
			} else {
				responseInquery = metodePayment(calLembarUang, calPayment, checkingPayment, remains, request);
			}
		} else if (request.getDataProducts().getNameProduct().equalsIgnoreCase("Cokelat")) {

			if (calLembarUang == 2) {
				if (calPayment == 15000) {
					checkingPayment = true;
					remains = calPayment - request.getDataProducts().getPriceProduct();
					responseInquery.setCheckingPayment(checkingPayment);
					responseInquery.setChange(remains);
					responseInquery.setLembarUang(calLembarUang);

				} else {
					checkingPayment = false;
					responseInquery.setCheckingPayment(checkingPayment);
					return responseInquery;
				}
			} else {
				responseInquery = metodePayment(calLembarUang, calPayment, checkingPayment, remains, request);
			}
		}
		
		log.info("response inquery :"+responseInquery);
		return responseInquery;
	}

	private ProductsResponse metodePayment(int calLembarUang, Double calPayment, boolean checkingPayment, Double remains,
			TransactionDTO requestDto) {
		ProductsResponse response = new ProductsResponse();
		
		if (calLembarUang == 3) {
			
			if (calPayment == 6000) {
				 if (requestDto.getTotalPayment()== 6000) {
					checkingPayment = true;
					remains = calPayment - requestDto.getTotalPayment();
				 } else {
					 checkingPayment = false;
						return responseError(checkingPayment, remains, response);
					
				 }
				
			} else if (calPayment == 15000) {
				if (requestDto.getTotalPayment() == 15000) {
					checkingPayment = true;
					remains =  calPayment -requestDto.getTotalPayment();
				} else {
					checkingPayment = false;
					return responseError(checkingPayment, remains, response);
				
				}
			} else {
				checkingPayment = false;
				return responseError(checkingPayment, remains, response);
			}
		} else if (calLembarUang == 1) {
			log.info("calLembarUang  " + calLembarUang  +" "+calPayment);
			if (calPayment == 10000) {
				if (requestDto.getTotalPayment() == 10000) {
					checkingPayment = true;
					remains = calPayment - requestDto.getTotalPayment();
				} else {
					checkingPayment = false;
					return responseError(checkingPayment, remains, response);
				}
			} else if (calPayment == 20000) {
				if (calPayment >= requestDto.getTotalPayment()) {
				remains = calPayment - requestDto.getTotalPayment();
				checkingPayment = true;
				} else {
					checkingPayment = false;
					return responseError(checkingPayment, remains, response);
				
				}
			} else if (calPayment == 50000) {

				if (calPayment >= requestDto.getTotalPayment()) {

					remains = calPayment - requestDto.getTotalPayment();
					checkingPayment = true;
				} else {
					checkingPayment = false;
					return responseError(checkingPayment, remains, response);
				}
			} else {
				checkingPayment = false;
				return responseError(checkingPayment, remains, response);
			}
		} else if (calLembarUang == 2 ) {
			if (calPayment == 10000) {
				if (requestDto.getTotalPayment() == 10000) {
					checkingPayment = true;
					remains =  calPayment - requestDto.getTotalPayment();
				} else {
					checkingPayment = false;
					return responseError(checkingPayment, remains, response);
				}
			} else {
				checkingPayment = false;
				return responseError(checkingPayment, remains, response);
			}
		} else if (calLembarUang == 4) {
			if (calPayment == 8000) {
				if (requestDto.getTotalPayment() == 8000) {
					checkingPayment = true;
					remains =  calPayment - requestDto.getTotalPayment();
				} else {
					checkingPayment = false;
					return responseError(checkingPayment, remains, response);
				}
			} else {
				checkingPayment = false;
				return responseError(checkingPayment, remains, response);
			}
		} else if (calLembarUang == 5) {
			if (calPayment == 10000) {
				if (requestDto.getTotalPayment() == 10000) {
					checkingPayment = true;
					remains = calPayment - requestDto.getTotalPayment();
				
				}
			} else {
				checkingPayment = false;
				return responseError(checkingPayment, remains, response);
			}
			
		} else if (calLembarUang == 6) {
			if (calPayment == 12000) {
				if (requestDto.getTotalPayment() == 12000) {
					checkingPayment = true;
					remains =  calPayment - requestDto.getTotalPayment();
				
				}
			} else {
				checkingPayment = false;
				return responseError(checkingPayment, remains, response);
			}
			
		} else {
			checkingPayment = false;
			return responseError(checkingPayment, remains, response);
		}

		response.setLembarUang(calLembarUang);
		response.setChange(remains);
		response.setCheckingPayment(checkingPayment);

		return response;
	}
	
	
	private ProductsResponse responseError(boolean checkingPayment, Double remains, ProductsResponse response) {
		
		response.setChange(remains);
		response.setCheckingPayment(checkingPayment);
		return response;
	}

}
