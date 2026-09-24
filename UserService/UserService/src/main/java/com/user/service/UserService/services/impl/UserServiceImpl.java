package com.user.service.UserService.services.impl;

import com.user.service.UserService.ExternalServices.HotelService;
import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exceptions.ResourceNotFoundException;
import com.user.service.UserService.repositories.UserRepository;
import com.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    // add for openFeing Communication
    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    // Explicit constructor (No Lombok needed)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // 1. Get user from database with help of repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // 2. Fetch ratings from RatingService via Eureka service discovery
        // Replace RATING-SERVICE with the exact spring.application.name configured in RatingService
        Rating[] ratingOfUser = restTemplate.getForObject(
                "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
                Rating[].class
        );

        logger.info("Ratings retrieved for user {}: {}", userId, ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();


       List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get teh hotel
//        ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
        Hotel hotel = hotelService.getHotel(rating.getHotelId());


        rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        // 3. Attach ratings to user object
        user.setRatings(ratingList);

        return user;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User userDetails, String userId) {
        User existingUser = getUser(userId);



        return userRepository.save(existingUser);
    }
}