package com.user.service.UserService;

import com.user.service.UserService.ExternalServices.RatingService;
import com.user.service.UserService.entities.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating(){
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("this is build by fringClient").build();
//	 Rating saveRating = ratingService.CreateRating(rating);
//	 System.out.println("new rating created");
//	}

}
