package com.rentmycar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/View_All_Bookings")
	public ResponseEntity<?> getallBookings(){
		return ResponseEntity.ok(adminService.getAllBookings());
		
	}
	
	@GetMapping("/get_Approved_Cars")
	public ResponseEntity<?> getAllApprovedCars(){
		return ResponseEntity.ok(adminService.getAllApprovedCars());
	}
	
	
	
	
}
