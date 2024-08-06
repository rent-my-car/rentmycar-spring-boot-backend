package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Embeddable
public class CarFeatures {
	
	@Column(columnDefinition = "boolean default false")
	Boolean hasUsbCharger; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	Boolean hasBluetooth; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	Boolean hasPowerSteering; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	Boolean hasAirBags; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	Boolean hasAbs; 	// Boolean,default-false
	
	@Column(columnDefinition = "boolean default false")
	Boolean hasAc; 	// Boolean,default-false

}
