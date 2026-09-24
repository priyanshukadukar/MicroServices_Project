package com.hotel.HotelService.services;

import com.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    // Cretae

    Hotel create(Hotel hotel);

    // get all
    List<Hotel> getAll() ;

    // get by id
    Hotel get(String id);


}
