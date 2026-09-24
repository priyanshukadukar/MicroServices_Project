package com.user.service.UserService.controllers;

import com.user.service.UserService.entities.User;
import com.user.service.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // Generates the 'log' variable automatically
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // Generates constructor for 'private final UserService userService'
public class UserController {

    private final UserService userService;

    // Create User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Get Single User by ID
    int retrycount =1;

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingFallback")
    @RateLimiter(name = "userRateLimiter" ,fallbackMethod = "userFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {

        log.info("Retry Count:{}", retrycount);
        retrycount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // Fallback method for CircuitBreaker


    public ResponseEntity<User> userFallback(String userId, Exception ex) {

//        log.info("Fallback executed for userId: {} because service is down. Error: {}", userId, ex.getMessage());

        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("Returned dummy user because dependent services (Rating/Hotel) are down.")
                .userId(userId)
                .build();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUser();
        return ResponseEntity.ok(allUsers);
    }

    // Update User
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(user, userId);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}