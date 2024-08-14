package com.rentmycar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ResourceNotFoundException;
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
	
	@PatchMapping("delete/{carListingId}")
	@Operation(description = "Soft delete a Car with CarListingId")
	public ResponseEntity<?> softDeleteCarById(@PathVariable Long carListingId){
		System.out.println("in soft delete" + carListingId);
		return ResponseEntity.ok(adminService.softDeleteCarById(carListingId));
	}

	// get all car_listing pending approvals
		@Operation(description = "get all car_listing pending approvals")
		@GetMapping("/pending_approvals")
		public ResponseEntity<?> getAllPendingApprovals() {
			return ResponseEntity.status(HttpStatus.OK).body(
					adminService.getAllPendingApprovals().orElseThrow(()->new ResourceNotFoundException("No Pending Approvals Found!!")));
		}

		// get deatil about pending approval for a particualr car_listing_id
	@Operation(description = "get deatil about pending approval for a particualr car_listing_id")
	@GetMapping("/pendingCarListingId")
	public ResponseEntity<?> getPendingApprovalByCarListingId(Long pendingCarListingId) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(adminService.getPendingApprovalByCarListingId(pendingCarListingId)
							.orElseThrow(()->new ResourceNotFoundException("No details found!!")));
		}

	
	
}
