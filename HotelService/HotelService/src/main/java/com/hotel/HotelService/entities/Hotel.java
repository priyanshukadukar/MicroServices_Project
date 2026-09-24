package com.hotel.HotelService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String Id;
    private String name;
    private String location;
    private  String  about;
}
