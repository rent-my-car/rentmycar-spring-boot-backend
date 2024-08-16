package com.rentmycar.dao;

import java.util.Optional;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.CarPricing;

public interface CarPricingDao extends JpaRepository<CarPricing, Long> {

	Optional<CarPricing> findByPricePerHrAndPricePerDayAndSecurityDeposit(Double pricePerHr, Double pricePerDay,
			Double securityDeposit);

//	@Column(nullable = false)
//	Double pricePerHr; 	// Boolean,not null
//	
//	@Column(nullable = false)
//	Double pricePerDay; 	// Boolean,not null
//	
//	@Column(nullable = false)
//	Double securityDeposit; 	// double,not null
}
