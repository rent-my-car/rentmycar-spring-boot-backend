package com.rentmycar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.Booking;

public interface BookingDao extends JpaRepository<Booking, Long> {

}
