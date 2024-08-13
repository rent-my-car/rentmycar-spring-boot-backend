package com.rentmycar.dto;

import com.rentmycar.entity.FuelTypeEnum;
import com.rentmycar.entity.TransmisssionTypeEnum;
import lombok.Data;

@Data
public class CarCardDto extends BaseDto{
    
	private String brand; // varchar(20),not null

	private String model; // varchar(20),not null

	private FuelTypeEnum fuelTypeEnum; // varchar(10), not null

	private int seatingCapacity; // tinyint(1-6),not null
	
	private int noOfTrips;

	private TransmisssionTypeEnum transmissionTypeEnum;
	
	private Double pricePerHr; 	// Boolean,not null
	
	private Double pricePerDay;
}
