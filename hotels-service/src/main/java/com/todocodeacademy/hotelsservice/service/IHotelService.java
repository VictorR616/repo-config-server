package com.todocodeacademy.hotelsservice.service;

import com.todocodeacademy.hotelsservice.model.Hotel;

import java.util.List;

public interface IHotelService {

    List<Hotel> getHotelsByCityId(Long city_id);

}
