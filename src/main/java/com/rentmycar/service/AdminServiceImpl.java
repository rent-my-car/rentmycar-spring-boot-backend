package com.rentmycar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.BookingDao;
import com.rentmycar.dao.CarListingDao;
import com.rentmycar.dto.AddressDto;
import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.CarCardDto;
import com.rentmycar.dto.CarDetailsDto;
import com.rentmycar.dto.CarFeaturesDto;
import com.rentmycar.dto.CarPricingDto;
import com.rentmycar.dto.PendingCarApprovalDetailDto;
import com.rentmycar.dto.UserDto;
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
	
	// get all car_listing pending approvals
		@Override
		public Optional<List<CarCardDto>> getAllPendingApprovals() {
			List<CarListing> carListings = carListingDao.findByIsApprovedAndIsDeleted(false, false)
					.orElseThrow(() -> new ResourceNotFoundException("no pending approvals"));
			return Optional.of(carListings.stream().map((carListing) -> {
				CarCardDto carCardDto = mapper.map(carListing.getCar(), CarCardDto.class);
				mapper.map(carListing.getCarPricing(), carCardDto);
				mapper.map(carListing, carCardDto);
				return carCardDto;
			}).collect(Collectors.toList()));
		}

		// get specific pending approval deatails by car_listing_id
		@Override
		public Optional<PendingCarApprovalDetailDto> getPendingApprovalByCarListingId(Long carListingId) {
			CarListing pCarListing = carListingDao.findById(carListingId)
					.orElseThrow(() -> new ResourceNotFoundException("car_listing_doesn't exist"));
			PendingCarApprovalDetailDto pendingCarApprovalDetailDto = new PendingCarApprovalDetailDto(new CarDetailsDto(),
					new CarPricingDto(), new CarFeaturesDto(), new AddressDto(), new UserDto());

			mapper.map(pCarListing.getCar(), pendingCarApprovalDetailDto.getCarDetailsDto());
			mapper.map(pCarListing, pendingCarApprovalDetailDto.getCarDetailsDto());
			mapper.map(pCarListing.getCarPricing(), pendingCarApprovalDetailDto.getCarPricingDto());
			mapper.map(pCarListing.getCar().getCarFeatures(), pendingCarApprovalDetailDto.getCarFeaturesDto());
			mapper.map(pCarListing.getAddress(), pendingCarApprovalDetailDto.getCarAddressDto());
			mapper.map(pCarListing.getHost(), pendingCarApprovalDetailDto.getOwner());

			return Optional.of(pendingCarApprovalDetailDto);
		}

	

}
