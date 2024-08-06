package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Embeddable
public class CarPricing {
	
	@Column(nullable = false)
	Double pricePerHr; 	// Boolean,not null
	
	@Column(nullable = false)
	Double pricePerDay; 	// Boolean,not null
	
	@Column(nullable = false)
	Double securityDeposit; 	// double,not null
}
