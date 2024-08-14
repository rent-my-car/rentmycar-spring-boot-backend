package com.rentmycar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentmycar.entity.Address;
import com.rentmycar.entity.Booking;
import com.rentmycar.entity.CarListing;
import com.rentmycar.entity.User;

public interface CarListingDao extends JpaRepository<CarListing, Long> {

	// get all car-listing in a particular address
	List<CarListing> findByAddress(Address address);

	boolean existsByRegistrationNo(String registrationNo);

	// method to get car details from date and city
	@Query("select c from CarListing c where c.address.city = :city and c.isAvailable=true and c.isDeleted=false and c.isApproved=true")
	Optional<List<CarListing>> getCarListCarListingByCity(String city);
	
	List<CarListing> findByIsApproved(Boolean isApproved);

	Optional<List<CarListing>> findByIsApprovedAndIsDeleted(boolean isDeleted, boolean isApproved);
	
	// get car_listing by host_id
	Optional<List<CarListing>> findByHost(User host);


}
