package com.user.service.UserService.repositories;

import com.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {

    // if you want to implemernt custom method write here
}
