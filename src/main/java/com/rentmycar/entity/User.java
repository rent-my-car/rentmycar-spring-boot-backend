package com.rentmycar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
public class User extends BaseEntity {

	@Column(length = 25, nullable = false)
	private String firstName;

	@Column(length = 25, nullable = false)
	private String lastName;

	@Column(length = 12, nullable = false)
	private String mobile;

	@Column(length = 20, unique = true, nullable = false)
	private String email;

	@Column(length = 70, nullable = false)
	private String password;

	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean isDeleted;

	@Enumerated(EnumType.STRING)
	@Column(length = 5)
	private UserRoleEnum roleEnum;

// *****************************************************************************************
	
	// User 1<---->* Address
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // mandatory , otherwise
																					// ,MappingException !
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
	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookingList = new ArrayList<>();

	public void addBooking(Booking booking) {
		bookingList.add(booking);
		booking.setGuest(this);
	}
	
// ********************************************************************************************

	// Guest 1 <------> *Review
	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviewList = new ArrayList<Review>();

	public void addReview(Review review) {
		reviewList.add(review);
		review.setGuest(this);
	}

//**********************************************************************************************
	
	// User 1 <------> 1 DrivingLicense
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private DrivingLicense drivingLicense;

	public void adddrivingLicense(DrivingLicense drivingLicense) {
		if (drivingLicense == null) {
			throw new RuntimeException("driving license is null");
		}
		this.drivingLicense = drivingLicense;
		drivingLicense.setUser(this);
	}

// ********************************************************************************************

	// Host 1 <---------> * CarHostAddressPricing
	@OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CarHostAddressPricing> carHostAddressPricingList = new ArrayList<CarHostAddressPricing>();
	
	public void addCarHostAddressPricing(CarHostAddressPricing carHostAddressPricing) {
		this.carHostAddressPricingList.add(carHostAddressPricing);
		carHostAddressPricing.setHost(this);
	}
	
//***********************************************************************************************
	
	
}
