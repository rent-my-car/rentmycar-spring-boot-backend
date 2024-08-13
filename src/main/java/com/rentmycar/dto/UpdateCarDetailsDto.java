package com.rentmycar.dto;

import com.rentmycar.entity.FuelTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarDetailsDto {

	private FuelTypeEnum fuelTypeEnum; // varchar(10), not null

	int kmDriven; // int,not null

	int fuelMeter; // tinyint(1-10),not null

	int spareTyreCount; // tinyint,not null

}
