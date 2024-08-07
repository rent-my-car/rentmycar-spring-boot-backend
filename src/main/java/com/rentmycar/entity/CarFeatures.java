package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "car_features")
public class CarFeatures extends BaseEntity{
	
	@Column(columnDefinition = "boolean default false")
	private Boolean hasUsbCharger; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	private Boolean hasBluetooth; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	private Boolean hasPowerSteering; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	private Boolean hasAirBags; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	private Boolean hasAbs; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	private Boolean hasAc; 	// Boolean,default-false

}
