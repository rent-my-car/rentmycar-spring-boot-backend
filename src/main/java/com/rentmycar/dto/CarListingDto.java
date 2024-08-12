package com.rentmycar.dto;

import lombok.Data;

@Data
public class CarListingDto extends BaseDto{
	int kmDriven; // int,not null

	int fuelMeter; // tinyint(1-10),not null

	String carImage; // varchar(20),

	int noOfTrips;

	Boolean isAvailable; // Boolean,default-true

	Boolean isDeleted; // Booleane,default-false

	Boolean isApproved; // Boolean,default-false

	String registrationNo; // varchar(20),not null

	int spareTyreCount; // tinyint,not null
}
