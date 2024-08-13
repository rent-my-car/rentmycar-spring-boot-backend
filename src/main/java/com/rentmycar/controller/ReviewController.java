package com.rentmycar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.dto.ReviewDto;
import com.rentmycar.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	//method to add review
	@PostMapping("/add_review/{bookingId}/{userId}")
	@Operation(description="add review by guest")
	public ResponseEntity<?> addAddress(@RequestBody ReviewDto reviewDto ,@PathVariable Long bookingId, @PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(reviewDto,bookingId,userId)
				.orElseThrow(() -> new ApiException("inernal server error")));
	}
	
	//method to update review
	@PatchMapping("/update_review/{reviewId}")
	@Operation(description="update review")
	public ResponseEntity<?> updateAddress(@RequestBody ReviewDto reviewDto ,@PathVariable Long reviewId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.updateReview(reviewDto,reviewId)
				.orElseThrow(() -> new ApiException("inernal server error")));
	}
}
