package com.todocodeacademy.citiesservice.service;

import com.todocodeacademy.citiesservice.dto.CityDTO;

public interface ICityService {
    CityDTO getCitiesHotels(String name, String country);

}
