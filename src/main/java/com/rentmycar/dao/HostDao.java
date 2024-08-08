package com.rentmycar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.User;

public interface HostDao extends JpaRepository<User, Long> {

}
