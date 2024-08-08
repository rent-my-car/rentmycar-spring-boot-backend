package com.rentmycar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;

public interface GuestDao extends JpaRepository<User, Long>{
	
	Optional<List<User>> findByRoleEnum(UserRoleEnum roleEnum);
	
	
}
