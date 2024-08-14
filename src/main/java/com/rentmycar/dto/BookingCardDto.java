package com.rentmycar.dto;

import java.time.LocalDateTime;

import com.rentmycar.entity.FuelTypeEnum;
import com.rentmycar.entity.TransmisssionTypeEnum;

import lombok.Data;

@Data
public class BookingCardDto {
		
	private LocalDateTime pickUp;
	
	private LocalDateTime dropOff;
	
	private String brand; 

	private String model;

	private FuelTypeEnum fuelTypeEnum;

	private int seatingCapacity; 
	
	private int kmDriven; 
		
	private TransmisssionTypeEnum transmissionTypeEnum;
	
	
		
}

