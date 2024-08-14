package com.rentmycar.service;

import java.util.List;
import java.util.Optional;

import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.CarCardDto;
import com.rentmycar.dto.PendingCarApprovalDetailDto;
import com.rentmycar.dto.ViewAllBookingDto;

public interface AdminService {
	
	List<ViewAllBookingDto> getAllBookings();
	
	List<CarCardDto> getAllApprovedCars();
	
	//soft-delete car with carListingId
	ApiResponseDto softDeleteCarById(Long carListingId);
		
	// get all car_listing pending approvals
	public Optional<List<CarCardDto>> getAllPendingApprovals();
	
	// get specific pending approval deatails by car_listing_id
	public Optional<PendingCarApprovalDetailDto> getPendingApprovalByCarListingId(Long carListingId);


}
