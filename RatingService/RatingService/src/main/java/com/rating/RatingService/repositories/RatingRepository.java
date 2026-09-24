package com.rating.RatingService.repositories;

import com.rating.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//if we add mongoDB dtabase we used MongoRepository
public interface RatingRepository extends JpaRepository<Rating ,String> {


    //custom find the method
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

}
