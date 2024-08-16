package com.rentmycar.dto;

import lombok.Data;

@Data
public class ReviewDto extends BaseDto{

	private String reviewText;

	private int rating;
}
