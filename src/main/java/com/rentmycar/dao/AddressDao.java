package com.rentmycar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentmycar.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long> {

	// get distinct cities names
	@Query(value = "SELECT DISTINCT city FROM address", nativeQuery = true)
	List<String> getDistinctCityNames();
}
