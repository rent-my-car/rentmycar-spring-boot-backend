package com.rentmycar.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.BookingDao;
import com.rentmycar.dao.ReviewDao;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.ReviewDto;
import com.rentmycar.entity.Booking;
import com.rentmycar.entity.Review;
import com.rentmycar.entity.User;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private ModelMapper mapper;

	// method to add review
	@Override
	public Optional<ReviewDto> addReview(ReviewDto reviewDto, Long userId, Long bookingId) {
		Booking pBooking = bookingDao.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found!"));
		User pGuest = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		Review tReview = mapper.map(reviewDto, Review.class);
		pBooking.addReview(tReview);
		tReview.setGuest(pGuest);
		Review pReview = reviewDao.save(tReview);
		return Optional.of(mapper.map(pReview, ReviewDto.class));
	}

	// method to update review
	@Override
	public Optional<ReviewDto> updateReview(ReviewDto reviewDto, Long reviewId) {
		Review pReview = reviewDao.findById(reviewId)
				.orElseThrow(() -> new ResourceNotFoundException("Review Not Found!"));
		mapper.map(pReview, reviewDto);
		Review updatedReview = reviewDao.save(pReview);
		return Optional.of(mapper.map(updatedReview, ReviewDto.class));
	}

}
