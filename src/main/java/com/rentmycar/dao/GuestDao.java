package com.rentmycar.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rentmycar.entity.Guest;

public interface GuestDao extends JpaRepository<Guest, Long> {
		Optional<Guest> findByEmailAndPassword(String email, String password);
	}

