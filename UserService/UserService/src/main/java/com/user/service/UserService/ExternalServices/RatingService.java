package com.user.service.UserService.ExternalServices;

import com.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATINGSERVICE" )
public interface RatingService {

    //get method

    //post
 @PostMapping("/ratings")
    public Rating CreateRating(Rating values);

    //put
    @PutMapping("/rating/{ratingId}")
    public Rating UpdateRating(@PathVariable String ratingId , Rating rating);

 // delete
    @DeleteMapping("/rating/{ratingId}")
    public void DeleteRating(@PathVariable String ratingId);

}
