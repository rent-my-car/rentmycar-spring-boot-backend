package com.rentmycar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.CarListing;

public interface CarListingDao extends JpaRepository<CarListing, Long>{

}
