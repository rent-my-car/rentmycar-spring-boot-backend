package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "car_pricing")
public class CarPricing extends BaseEntity{
	
	@Column(nullable = false)
	Double pricePerHr; 	// Boolean,not null
	
	@Column(nullable = false)
	Double pricePerDay; 	// Boolean,not null
	
	@Column(nullable = false)
	Double securityDeposit; 	// double,not null
}
