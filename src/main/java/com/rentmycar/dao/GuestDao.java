package com.rentmycar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.User;

public interface GuestDao extends JpaRepository<User, Long> {

}
