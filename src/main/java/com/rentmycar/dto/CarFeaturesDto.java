package com.rentmycar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CarFeaturesDto extends BaseDto {

	private Boolean hasUsbCharger; // Boolean,default-false

	private Boolean hasBluetooth; // Boolean,default-false

	private Boolean hasPowerSteering; // Boolean,default-false

	private Boolean hasAirBags; // Boolean,default-false

	private Boolean hasAbs; // Boolean,default-false

	private Boolean hasAc; // Boolean,default-false

}
