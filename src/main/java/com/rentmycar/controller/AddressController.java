package com.rentmycar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.dto.AddressDto;
import com.rentmycar.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	// add address by user id
	@PostMapping("/{userId}")
	public ResponseEntity<?> addAddress(@RequestBody @Valid AddressDto addressDto, @PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressDto, userId)
				.orElseThrow(() -> new ApiException("inernal server error")));
	}

}
