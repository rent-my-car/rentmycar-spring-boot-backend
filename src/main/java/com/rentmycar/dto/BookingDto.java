package com.rentmycar.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto extends BaseDto {
	private LocalDateTime pickUp;
	private LocalDateTime dropOff;
	private double amount;
}