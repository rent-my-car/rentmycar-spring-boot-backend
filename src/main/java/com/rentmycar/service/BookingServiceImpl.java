package com.rentmycar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.dao.AddressDao;
import com.rentmycar.dao.BookingDao;
import com.rentmycar.dao.CarListingDao;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.AddressDto;
import com.rentmycar.dto.BookingCardDto;
import com.rentmycar.dto.BookingDto;
import com.rentmycar.dto.BookingResponseDto;
import com.rentmycar.dto.CarListingDto;
import com.rentmycar.dto.UserDto;
import com.rentmycar.entity.Address;
import com.rentmycar.entity.Booking;
import com.rentmycar.entity.BookingStatusEnum;
import com.rentmycar.entity.CarListing;
import com.rentmycar.entity.User;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CarListingDao carListingDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private ModelMapper mapper;

	public Optional<BookingResponseDto> addBooking(BookingDto bookingDto, Long guestId, Long guestAddressId,
			Long carListingId) {

		if (bookingDto.getPickUp().isAfter(bookingDto.getDropOff()))
			throw new RuntimeException("Pickup Date is invalid !");

		User guest = userDao.findById(guestId).orElseThrow(() -> new RuntimeException("Guest not found"));

		Address guestAddress = addressDao.findById(guestAddressId)
				.orElseThrow(() -> new RuntimeException("Guest address not found"));

		CarListing carListing = carListingDao.findById(carListingId)
				.orElseThrow(() -> new RuntimeException("Car Listing not found"));

		Booking tBooking = mapper.map(bookingDto, Booking.class);
		tBooking.setBookingStatusEnum(BookingStatusEnum.PENDING);
		guest.addBooking(tBooking);
		tBooking.setGuestAddress(guestAddress);
		carListing.addBooking(tBooking);
		Booking pBooking = bookingDao.save(tBooking);
		BookingResponseDto bookingResponseDto = mapper.map(pBooking, BookingResponseDto.class);
		bookingResponseDto.setGuest(mapper.map(guest, UserDto.class));
		bookingResponseDto.setCarListing(mapper.map(carListing, CarListingDto.class));
		bookingResponseDto.setGuestAddress(mapper.map(guestAddress, AddressDto.class));
		return Optional.of(bookingResponseDto);
	}

	@Override
	public Optional<List<BookingCardDto>> getPastBookings(Long userId) {
		User user = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		if (user.getRoleEnum().name() == "GUEST") {
			return Optional.of(user.getBookingList().stream()
					.filter(booking -> booking.getPickUp().isBefore(LocalDateTime.now())).map(booking -> {
						BookingCardDto bookingCardDto = mapper.map(booking, BookingCardDto.class);
						mapper.map(booking.getCarListing(), bookingCardDto);
						mapper.map(booking.getCarListing().getCar(), bookingCardDto);
						return bookingCardDto;
					}).collect(Collectors.toList()));

		}
		 return Optional.of(user.getCarListingList().stream()
		            .flatMap(carListing -> carListing.getBookingList().stream())
		            .filter(booking -> booking.getPickUp().isBefore(LocalDateTime.now()))
		            .map(booking -> {
		                BookingCardDto bookingCardDto = mapper.map(booking, BookingCardDto.class);
		                mapper.map(booking.getCarListing(), bookingCardDto);
		                mapper.map(booking.getCarListing().getCar(), bookingCardDto);
		                return bookingCardDto;
		            })
		            .collect(Collectors.toList()));				
	}
}
