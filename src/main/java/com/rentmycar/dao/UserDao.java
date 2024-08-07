package com.rentmycar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;

public interface UserDao extends JpaRepository<User, Long> {
	Optional<User> findByEmailAndPasswordAndRoleEnum(String email, String password, UserRoleEnum roleEnum);
}
