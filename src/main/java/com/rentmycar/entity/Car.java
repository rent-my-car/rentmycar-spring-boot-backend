package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Table(name = "car")
@Entity
public class Car extends BaseEntity {

	@Column(length = 20, nullable = false)
	String brand; // varchar(20),not null

	@Column(length = 20, nullable = false)
	String model; // varchar(20),not null

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	FuelTypeEnum fuelTypeEnum; // varchar(10), not null
	
	
	int kmDriven; // int,not null
	
	@Column(columnDefinition = "TINYINT", nullable = false)
	int fuelMeter; // tinyint(1-10),not null
	
	@Column(length = 20)
	String carImage; // varchar(20),
	
	@Column(nullable = false)
	int noOfTrips; // int,not null
	
	@Column(columnDefinition = "TINYINT", nullable = false )
	int seatingCapacity; // tinyint(1-6),not null
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	Boolean isAvailable; // Boolean,default-true
	
	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	Boolean isDeleted; // Booleane,default-false
	
	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	Boolean isApproved; // Boolean,default-false
	
	@Column(length = 20, nullable = false)
	String registrationNo; // varchar(20),not null
	
	@Column(columnDefinition = "TINYINT NOT NULL")
	int spareTyreCount; // tinyint,not null
	
	@Embedded
	CarFeatures carFeatures; // Embedded,
	
	@Embedded
	CarPricing carPricing; // Embedded
}
