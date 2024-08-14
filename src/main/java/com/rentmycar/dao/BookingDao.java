package com.rentmycar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.Booking;
import com.rentmycar.entity.BookingStatusEnum;

public interface BookingDao extends JpaRepository<Booking, Long> {
	Optional<List<Booking>> findByBookingStatusEnum(BookingStatusEnum bookingStatusEnum);
}
