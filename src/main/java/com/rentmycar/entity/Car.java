package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "car")
@Entity
public class Car extends BaseEntity {

	@Column(length = 20, nullable = false)
	private String brand; // varchar(20),not null

	@Column(length = 20, nullable = false)
	private String model; // varchar(20),not null

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private FuelTypeEnum fuelTypeEnum; // varchar(10), not null

	@Column(columnDefinition = "TINYINT", nullable = false)
	private int seatingCapacity; // tinyint(1-6),not null

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private TransmisssionTypeEnum transmissionTypeEnum;

//*************************************************************
	// Car * ------> 1 CarFeatures
	// many cars can have same features
	@ManyToOne(fetch = FetchType.LAZY)
	private CarFeatures carFeatures;

//*************************************************************
}
