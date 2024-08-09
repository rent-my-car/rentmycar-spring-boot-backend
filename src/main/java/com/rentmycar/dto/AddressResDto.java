package com.rentmycar.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResDto {
	@NotNull
	Long userId;

	@NotNull
	AddressDto addressDto;
}
