package com.rentmycar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.dto.RegisterHostRequestDto;
import com.rentmycar.service.HostService;

@RestController("/host")
public class HostController {

	@Autowired
	HostService hostService;

	@PostMapping("/register_host")
	public ResponseEntity<?> registerHost(@RequestBody @Valid RegisterHostRequestDto registerHostRequestDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(hostService.registerHost(registerHostRequestDto)
				.orElseThrow(() -> new ApiException("internal server error")));

	}

	@GetMapping("/get_all_host")
	public ResponseEntity<?> getAllHost(){

		return ResponseEntity.ok(hostService.getAllHost());
	}

}
