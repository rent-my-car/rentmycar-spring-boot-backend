package com.rentmycar.dto;

import com.rentmycar.entity.FuelTypeEnum;
import com.rentmycar.entity.TransmisssionTypeEnum;

import lombok.Data;

@Data
public class CarDto extends BaseDto {

	private String brand; // varchar(20),not null

	private String model; // varchar(20),not null

	private FuelTypeEnum fuelTypeEnum; // varchar(10), not null

	private int seatingCapacity; // tinyint(1-6),not null

	private TransmisssionTypeEnum transmissionTypeEnum;

}
