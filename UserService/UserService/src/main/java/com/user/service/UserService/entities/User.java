package com.user.service.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name = "micro_users")


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Generates unique String UUIDs
    @Column(name = "id")
    private String userId;

    private String name;
    private String email;
    private String about;

    @Transient  // its means it not store in database
    private List<Rating> ratings = new ArrayList<>();
}
