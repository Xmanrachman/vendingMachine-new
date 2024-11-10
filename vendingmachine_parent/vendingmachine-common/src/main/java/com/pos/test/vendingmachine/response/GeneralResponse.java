package com.pos.test.vendingmachine.response;

/**
 * 
 */

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param <T>
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse<T> {

	public static final int STATUS_SUCCESS = 200;

	public static final int STATUS_FAILED = 400;

	private String info;
	private List<String> error_message;
	private String row;
	private String error_code;

	private int status;
	private List<T> data;

	private T entity;

	
}
