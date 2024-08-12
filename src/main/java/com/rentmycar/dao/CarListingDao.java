package com.rentmycar.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rentmycar.entity.Address;
import com.rentmycar.entity.CarListing;

public interface CarListingDao extends JpaRepository<CarListing, Long> {

	// get all car-listing in a particular address
	List<CarListing> findByAddress(Address address);

	boolean existsByRegistrationNo(String registrationNo);

}
