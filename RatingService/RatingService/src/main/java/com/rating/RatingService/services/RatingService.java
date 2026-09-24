package com.rating.RatingService.services;

import com.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create

    Rating create(Rating rating);

    // get all rating
    List<Rating> getAll();

    // get by id
    List<Rating> getByuserId(String userId);

    // get all by  Hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
