package com.rentmycar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.custom_exception.ConflictException;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.AddressDao;
import com.rentmycar.dao.CarDao;
import com.rentmycar.dao.CarFeatuesDao;
import com.rentmycar.dao.CarListingDao;
import com.rentmycar.dao.CarPricingDao;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.AddCarListingDto;
import com.rentmycar.dto.AddCarListingResponseDto;
import com.rentmycar.dto.AddressDto;
import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.CarCardDto;
import com.rentmycar.dto.CarDetailsDto;
import com.rentmycar.dto.CarDto;
import com.rentmycar.dto.CarFeaturesDto;
import com.rentmycar.dto.CarPricingDto;
import com.rentmycar.dto.GetCarListingResponseDto;
import com.rentmycar.dto.UpdateCarListingDto;
import com.rentmycar.dto.UserDto;
import com.rentmycar.entity.Address;
import com.rentmycar.entity.BookingStatusEnum;
import com.rentmycar.entity.Car;
import com.rentmycar.entity.CarFeatures;
import com.rentmycar.entity.CarListing;
import com.rentmycar.entity.CarPricing;
import com.rentmycar.entity.User;

@Service
@Transactional
public class CarListingServiceImpl implements CarListingService {

	@Autowired
	ModelMapper mapper;

	@Autowired
	CarFeatuesDao carFeatuesDao;

	@Autowired
	CarDao carDao;

	@Autowired
	CarPricingDao carPricingDao;

	@Autowired
	UserDao userDao;

	@Autowired
	AddressDao addressDao;

	@Autowired
	CarListingDao carListingDao;

	// add car listing by host id and host address id
	@Override
	public Optional<AddCarListingResponseDto> addCarListing(Long hostId, Long hostAddressId,
			AddCarListingDto addCarListingDto) {
		if (carListingDao.existsByRegistrationNo(addCarListingDto.getCarDetailsDto().getRegistrationNo())) {
			throw new ConflictException("registration no alreday exists");
		}
		User pHost = userDao.findById(hostId).orElseThrow(() -> new ResourceNotFoundException("invalid host id"));
		Address pHostAddress = addressDao.findById(hostAddressId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid address id"));
		// mappping DTOs to entities
		CarListing tCarListing = mapper.map(addCarListingDto.getCarDetailsDto(), CarListing.class);
		Car tCar = mapper.map(addCarListingDto.getCarDetailsDto(), Car.class);
		CarPricing tCarPricing = mapper.map(addCarListingDto.getCarPricingDto(), CarPricing.class);
		CarFeatures tCarFeatures = mapper.map(addCarListingDto.getCarFeaturesDto(), CarFeatures.class);

		//
		CarFeatures pCarFeatures = carFeatuesDao
				.findByHasUsbChargerAndHasBluetoothAndHasPowerSteeringAndHasAirBagsAndHasAbsAndHasAc(
						tCarFeatures.getHasUsbCharger(), tCarFeatures.getHasBluetooth(),
						tCarFeatures.getHasPowerSteering(), tCarFeatures.getHasAirBags(), tCarFeatures.getHasAbs(),
						tCarFeatures.getHasAc())
				.orElseGet(() -> carFeatuesDao.save(tCarFeatures));

		tCar.setCarFeatures(pCarFeatures);
		Car pCar = carDao.findByBrandAndModelAndFuelTypeEnumAndSeatingCapacityAndTransmissionTypeEnum(tCar.getBrand(),
				tCar.getModel(), tCar.getFuelTypeEnum(), tCar.getSeatingCapacity(), tCar.getTransmissionTypeEnum())
				.orElseGet(() -> carDao.save(tCar));

		CarPricing pCarPricing = carPricingDao
				.findByPricePerHrAndPricePerDayAndSecurityDeposit(tCarPricing.getPricePerHr(),
						tCarPricing.getPricePerDay(), tCarPricing.getSecurityDeposit())
				.orElseGet(() -> carPricingDao.save(tCarPricing));

		tCarListing.setIsApproved(false);
		tCarListing.setNoOfTrips(0);
		tCarListing.setIsAvailable(true);
		tCarListing.setIsDeleted(false);
		tCarListing.setIsApproved(false);

		pHost.addCarListing(tCarListing);
		tCarListing.setAddress(pHostAddress);
		tCarListing.setCarPricing(pCarPricing);
		tCarListing.setCar(pCar);

		CarListing pCarListing = carListingDao.save(tCarListing);

		AddCarListingResponseDto addCarListingResponseDto = mapper.map(pCarListing, AddCarListingResponseDto.class);
		addCarListingResponseDto.setHostDto(new UserDto());
		addCarListingResponseDto.setHostAddressDto(new AddressDto());
		addCarListingResponseDto.setCarPricingDto(new CarPricingDto());
		addCarListingResponseDto.setCarDto(new CarDto());
		addCarListingResponseDto.setCarFeaturesDto(new CarFeaturesDto());
		mapper.map(pHost, addCarListingResponseDto.getHostDto());
		mapper.map(pHostAddress, addCarListingResponseDto.getHostAddressDto());
		mapper.map(pCarPricing, addCarListingResponseDto.getCarPricingDto());
		mapper.map(pCar, addCarListingResponseDto.getCarDto());
		mapper.map(pCarFeatures, addCarListingResponseDto.getCarFeaturesDto());

		return Optional.of(addCarListingResponseDto);

		//
	}

	// method to get all cars available for booking
	@Override
	public Optional<List<CarCardDto>> getCarListing(String city, LocalDateTime pickUp, LocalDateTime dropOff) {
		List<CarListing> carListings = carListingDao.getCarListCarListingByCity(city);

		if (carListings.isEmpty()) {
			throw new ResourceNotFoundException("No car list for particular city");
		}
		List<CarCardDto> availableCars = carListings.stream()
				.filter(carListing -> carListing.getIsApproved() == true && carListing.getIsAvailable() == true
						&& carListing.getIsDeleted() == false)
				.filter(carListing -> carListing.getBookingList().stream()
						.filter(booking -> booking.getBookingStatusEnum().equals(BookingStatusEnum.SUCCESS))
						.noneMatch(booking -> (booking.getPickUp().minusHours(5).isBefore(dropOff)
								&& booking.getDropOff().plusHours(5).isAfter(pickUp))))
				.map(carListing -> {
					// Map CarListing to CarCardDetailsDto using ModelMapper
					CarCardDto carCardDetailsDto = mapper.map(carListing.getCar(), CarCardDto.class);
					mapper.map(carListing.getCarPricing(), carCardDetailsDto);
					mapper.map(carListing, carCardDetailsDto);
					return carCardDetailsDto;
				}).collect(Collectors.toList());
		return Optional.of(availableCars);
	}

	// get car Listing by car_listing_id
	@Override
	public Optional<GetCarListingResponseDto> getCarListingByCarListingId(Long carListingId) {
		CarListing pCarListing = carListingDao.findById(carListingId)
				.orElseThrow(() -> new ResourceNotFoundException("car_listing_doesn't exist"));

		GetCarListingResponseDto getCarListingResponseDto = new GetCarListingResponseDto(new CarDetailsDto(),
				new CarPricingDto(), new CarFeaturesDto(), new AddressDto());

		mapper.map(pCarListing.getCar(), getCarListingResponseDto.getCarDetailsDto());
		mapper.map(pCarListing, getCarListingResponseDto.getCarDetailsDto());
		mapper.map(pCarListing.getCarPricing(), getCarListingResponseDto.getCarPricingDto());
		mapper.map(pCarListing.getCar().getCarFeatures(), getCarListingResponseDto.getCarFeaturesDto());
		mapper.map(pCarListing.getAddress(), getCarListingResponseDto.getCarAddressDto());

		return Optional.of(getCarListingResponseDto);
	}

	// update car_listing by car_listing_id
	@Override
	public Optional<GetCarListingResponseDto> updateCarListingByCarListingId(Long carListingId,
			UpdateCarListingDto updateCarListingDto) {
		CarListing pCarListing = carListingDao.findById(carListingId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid car_listing_id"));
		mapper.map(updateCarListingDto.getUpdateCarDetailsDto(), pCarListing);
//		mapper.map(updateCarListingDto.getUpdateCarDetailsDto(), pCarListing.getCar());
		mapper.map(updateCarListingDto.getCarAddressDto(), pCarListing.getAddress());

		CarPricing pCarPricing = carPricingDao.findByPricePerHrAndPricePerDayAndSecurityDeposit(
				updateCarListingDto.getCarPricingDto().getPricePerHr(),
				updateCarListingDto.getCarPricingDto().getPricePerDay(),
				updateCarListingDto.getCarPricingDto().getSecurityDeposit()).orElseGet(() -> {
					CarPricing tCarPricing = mapper.map(updateCarListingDto.getCarPricingDto(), CarPricing.class);
					tCarPricing.setId(null);
					return carPricingDao.save(tCarPricing);
				});

		Car pCar = carDao
				.findByBrandAndModelAndFuelTypeEnumAndSeatingCapacityAndTransmissionTypeEnum(
						pCarListing.getCar().getBrand(), pCarListing.getCar().getModel(),
						updateCarListingDto.getUpdateCarDetailsDto().getFuelTypeEnum(),
						pCarListing.getCar().getSeatingCapacity(), pCarListing.getCar().getTransmissionTypeEnum())
				.orElseGet(() -> {
					Car tCar = mapper.map(pCarListing.getCar(), Car.class);
					tCar.setId(null);
					return carDao.save(tCar);
				});

		pCarListing.setCarPricing(pCarPricing);
		pCarListing.setCar(pCar);
		CarListing updatedCarListing = carListingDao.save(pCarListing);

		GetCarListingResponseDto getCarListingResponseDto = new GetCarListingResponseDto(new CarDetailsDto(),
				new CarPricingDto(), new CarFeaturesDto(), new AddressDto());

		mapper.map(updatedCarListing.getCar(), getCarListingResponseDto.getCarDetailsDto());
		mapper.map(updatedCarListing, getCarListingResponseDto.getCarDetailsDto());
		mapper.map(updatedCarListing.getCarPricing(), getCarListingResponseDto.getCarPricingDto());
		mapper.map(updatedCarListing.getCar().getCarFeatures(), getCarListingResponseDto.getCarFeaturesDto());
		mapper.map(updatedCarListing.getAddress(), getCarListingResponseDto.getCarAddressDto());
		return Optional.of(getCarListingResponseDto);
	}

	// method to get specific details of car
	@Override
	public Optional<CarCardDto> getSpecificCarDetails(Long carListingId) {
		CarListing carListing = carListingDao.findById(carListingId)
				.orElseThrow(() -> new ResourceNotFoundException("Car Not Found!"));
		if(carListing.getIsApproved() == true && carListing.getIsAvailable() == true && carListing.getIsDeleted() == false)
		{
			CarCardDto carCardDto = mapper.map(carListing, CarCardDto.class);
			mapper.map(carListing.getAddress(), carCardDto);
			mapper.map(carListing.getCar(), carCardDto);
			mapper.map(carListing.getCarPricing(), carCardDto);
			mapper.map(carListing.getBookingList(), carCardDto);
			return Optional.of(carCardDto);
		}else
			return Optional.empty();		
	}

	// get car cards by host_id
	public Optional<List<CarCardDto>> getPendingApprovalsByHostId(Long hostId) {
		User pHost = userDao.findById(hostId).orElseThrow(() -> new ResourceNotFoundException("invalid host_id"));
		List<CarListing> carListings = carListingDao.findByHost(pHost)
				.orElseThrow(() -> new ResourceNotFoundException("no car listed for this host_id"));

		return Optional.of(carListings.stream()
				.filter(carListing -> !carListing.getIsApproved() && !carListing.getIsDeleted()).map((carListing) -> {
					CarCardDto carCardDto = mapper.map(carListing.getCar(), CarCardDto.class);
					mapper.map(carListing.getCarPricing(), carCardDto);
					mapper.map(carListing, carCardDto);
					return carCardDto;
				}).collect(Collectors.toList()));
	}

	// get confirmed car_listings by host_id
	@Override
	public Optional<List<CarCardDto>> getConfirmedApprovalsByHostId(Long hostId) {
		User pHost = userDao.findById(hostId).orElseThrow(() -> new ResourceNotFoundException("invalid host_id"));
		List<CarListing> carListings = carListingDao.findByHost(pHost)
				.orElseThrow(() -> new ResourceNotFoundException("no car listed for this host_id"));

		return Optional.of(carListings.stream()
				.filter(carListing -> carListing.getIsApproved() && !carListing.getIsDeleted()).map((carListing) -> {
					CarCardDto carCardDto = mapper.map(carListing.getCar(), CarCardDto.class);
					mapper.map(carListing.getCarPricing(), carCardDto);
					mapper.map(carListing, carCardDto);
					return carCardDto;
				}).collect(Collectors.toList()));
	}

	

	//
}
