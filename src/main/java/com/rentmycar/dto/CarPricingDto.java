package com.rentmycar.dto;

import com.rentmycar.entity.BaseEntity;

import lombok.Data;

@Data
public class CarPricingDto extends BaseEntity {

	Double pricePerHr; // Boolean,not null

	Double pricePerDay; // Boolean,not null

	Double securityDeposit; // double,not null
}
