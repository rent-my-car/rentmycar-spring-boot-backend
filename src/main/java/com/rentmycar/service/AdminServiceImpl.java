package com.rentmycar.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.BookingDao;
import com.rentmycar.dao.CarListingDao;
import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.CarCardDto;
import com.rentmycar.dto.ViewAllBookingDto;
import com.rentmycar.entity.Booking;
import com.rentmycar.entity.BookingStatusEnum;
import com.rentmycar.entity.CarListing;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private CarListingDao carListingDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<ViewAllBookingDto> getAllBookings() {
		List<Booking> bookingList= bookingDao.findByBookingStatusEnum(BookingStatusEnum.SUCCESS).orElseThrow(()->new ResourceNotFoundException("No Bookings Found!"));
		return bookingList.stream().map((booking) -> {
			ViewAllBookingDto viewAllBookingDto = mapper.map(booking,ViewAllBookingDto.class);
			mapper.map(booking.getCarListing(), viewAllBookingDto);
			mapper.map(booking.getCarListing().getCar(), viewAllBookingDto);
			mapper.map(booking.getGuest(), viewAllBookingDto);
			return viewAllBookingDto;
		}).collect(Collectors.toList());
		
	}

	@Override
	public List<CarCardDto> getAllApprovedCars() {
		List<CarListing> approvedCarList=carListingDao.findByIsApproved(true);
		return approvedCarList.stream().map((CarListing) -> {
			CarCardDto viewApprovedCarCardDto = mapper.map(CarListing.getCar(),CarCardDto.class);
			mapper.map(CarListing.getCarPricing(), viewApprovedCarCardDto);
			mapper.map(CarListing, viewApprovedCarCardDto);
			return viewApprovedCarCardDto;
		}).collect(Collectors.toList());
	}
	
	@Override
	public ApiResponseDto softDeleteCarById(Long carListingId) {
		CarListing	pCarListing = carListingDao.findById(carListingId).orElseThrow(() -> new ResourceNotFoundException("Invalid CarListing Id."));

		if (pCarListing.getIsDeleted())
			throw new ResourceNotFoundException("Car is already deleted.");

		pCarListing.setIsDeleted(true);
		System.out.println("Car delete status made true");
		return new ApiResponseDto("Car Deleted Successfully!");
		
	}

	
	

}
