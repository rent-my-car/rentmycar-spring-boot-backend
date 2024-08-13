package com.rentmycar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.Review;

public interface ReviewDao extends JpaRepository<Review, Long>{

}
