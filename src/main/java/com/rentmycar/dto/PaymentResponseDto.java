package com.rentmycar.dto;

import java.time.LocalDateTime;

import com.rentmycar.entity.BookingStatusEnum;

import lombok.Data;

@Data
public class PaymentResponseDto extends BaseDto{

	private  BookingStatusEnum bookingStatusEnum;
	
	private LocalDateTime paymentDateTime;
	
	private String transactionId;
	
	private Double amount; // double,not null

}
