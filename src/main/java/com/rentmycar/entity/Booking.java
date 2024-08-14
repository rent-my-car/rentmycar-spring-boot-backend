package com.rentmycar.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class Booking extends BaseEntity {
	@Column(nullable = false)
	private LocalDateTime pickUp; // DateTime,not null

	@Column(nullable = false)
	private LocalDateTime dropOff; // DateTime,not null

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	@ColumnDefault("'PENDING'")
	private BookingStatusEnum bookingStatusEnum; // not null,default - PENDING

	@Column(nullable = false)
	private Double amount; // double,not null

	private LocalDateTime paymentDateTime; // DateTime

	@Column(columnDefinition = "char(50)")
	private String transactionId; // char (#### 50.

//*****************************************************************************************************

	// Booking * <---> 1 Guest
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guest_id", nullable = false)
	private User guest;

//****************************************************************************************************

	// Booking * -------> 1 Address
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Address guestAddress;

//****************************************************************************************************

	// Booking * <-----------> 1 CarListing
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private CarListing carListing;

//****************************************************************************************************

	// Booking 1 <------> 1 Review
	@OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Review review;

	public void addReview(Review review) {
		if (review == null) {
			throw new RuntimeException("No Review");
		}
		this.review = review;
		review.setBooking(this);
	}

//****************************************************************************************************
}
