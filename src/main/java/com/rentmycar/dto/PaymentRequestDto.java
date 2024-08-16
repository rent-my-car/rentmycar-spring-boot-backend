package com.rentmycar.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PaymentRequestDto {

	private Double amount;
	
	private String cardNo;
	
	private String cardHolderName;
	
	private String expiryDate;
	
	private String cvv;

}
