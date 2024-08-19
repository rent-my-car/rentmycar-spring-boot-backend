package com.rentmycar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"email","roleEnum"}),
	    @UniqueConstraint(columnNames = {"mobile","roleEnum"})})
public class User extends BaseEntity {

	@Column(length = 30, nullable = false)
	private String firstName;

	@Column(length = 30, nullable = false)
	private String lastName;

	@Column(length = 12, nullable = false)
	private String mobile;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 70, nullable = false)
	private String password;

	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean isDeleted;

	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private UserRoleEnum roleEnum;

// *****************************************************************************************
	
	// User 1<---->* Address
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) // mandatory , otherwise
	private List<Address> addressList = new ArrayList<>();

	public void addAddress(Address address) {
		addressList.add(address);
		address.setUser(this);
	}

	public void deleteAddress(Address address) {
		addressList.remove(address);
		address.setUser(null);
	}

// *******************************************************************************************

	// Guest 1<---->* Booking
	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Booking> bookingList = new ArrayList<>();

	public void addBooking(Booking booking) {
		bookingList.add(booking);
		booking.setGuest(this);
	}
	
// ********************************************************************************************

	// Guest 1 <------> *Review
	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Review> reviewList = new ArrayList<Review>();

	public void addReview(Review review) {
		reviewList.add(review);
		review.setGuest(this);
	}

//**********************************************************************************************
	
	// User 1 <------> 1 DrivingLicense
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private DrivingLicense drivingLicense;

	public void addDrivingLicense(DrivingLicense drivingLicense) {
		if (drivingLicense == null) {
			throw new RuntimeException("driving license is null");
		}
		this.drivingLicense = drivingLicense;
		drivingLicense.setUser(this);
	}

// ********************************************************************************************

	// Host 1 <---------> * CarListing
	@OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CarListing> carListingList = new ArrayList<CarListing>();
	
	public void addCarListing(CarListing carListing) {
		this.carListingList.add(carListing);
		carListing.setHost(this);
	}
	
//***********************************************************************************************
	
	
}
