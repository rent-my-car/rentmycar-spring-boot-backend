package com.rentmycar.service;

import java.util.List;
import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.CarCardDto;
import com.rentmycar.dto.ViewAllBookingDto;

public interface AdminService {
	
	List<ViewAllBookingDto> getAllBookings();
	
	List<CarCardDto> getAllApprovedCars();
	
	//soft-delete car with carListingId
	ApiResponseDto softDeleteCarById(Long carListingId);
		
	
}
