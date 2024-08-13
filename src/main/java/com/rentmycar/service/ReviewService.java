package com.rentmycar.service;

import java.util.Optional;

import com.rentmycar.dto.ReviewDto;

public interface ReviewService {

	//method to add review
	Optional<ReviewDto> addReview(ReviewDto reviewDto,Long userId,Long bookingId);
	
	//method to update review
	Optional<ReviewDto> updateReview(ReviewDto reviewDto,Long userId);
}
