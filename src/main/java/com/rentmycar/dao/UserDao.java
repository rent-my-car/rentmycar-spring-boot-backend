package com.rentmycar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;

public interface UserDao extends JpaRepository<User, Long> {

	boolean existsByMobileAndEmailAndRoleEnum(String mobile, String email, UserRoleEnum roleEnum);

	boolean existsByMobileAndRoleEnum(String mobile, UserRoleEnum roleEnum);

	boolean existsByEmailAndRoleEnum(String email, UserRoleEnum roleEnum);

	Optional<User> findByEmailAndPasswordAndRoleEnum(String email, String password, UserRoleEnum roleEnum);

	// find list of users by user role
	Optional<List<User>> findByRoleEnum(UserRoleEnum roleEnum);

	// find user by email
	Optional<User> findByEmail(String email);

}
