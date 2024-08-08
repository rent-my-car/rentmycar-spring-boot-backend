package com.rentmycar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rentmycar.service.HostService;

@RestController("/host")
public class HostController {

	@Autowired
	HostService hostService;

	

	@GetMapping("/get_all_host")
	public ResponseEntity<?> getAllHost(){

		return ResponseEntity.ok(hostService.getAllHost());
	}

}
