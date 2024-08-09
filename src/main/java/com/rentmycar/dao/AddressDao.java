package com.rentmycar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long> {

}
