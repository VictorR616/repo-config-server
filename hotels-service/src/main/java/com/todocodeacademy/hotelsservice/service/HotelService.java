package com.todocodeacademy.hotelsservice.service;

import com.todocodeacademy.hotelsservice.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService implements IHotelService {

    List<Hotel> hotelsList = new ArrayList<>();

    @Override
    public List<Hotel> getHotelsByCityId(Long city_id) {
        List<Hotel> hotelCityList = new ArrayList<>();

        // Carga la bd "logica"
        this.loadHotels();

        // Busco si coincide la id
        for (Hotel h : hotelsList) {
            if (h.getCity_id() == city_id) {
                hotelCityList.add(h);
            }
        }

        // Devuelve la lista
        return hotelCityList;

    }


    public void loadHotels() {
        hotelsList.add(new Hotel(1L, "Paradise", 5, 1L));
        hotelsList.add(new Hotel(2L, "Sunset", 4, 2L));
        hotelsList.add(new Hotel(3L, "Mountaint Retreat", 3, 3L));
        hotelsList.add(new Hotel(4L, "Luxury Heaven", 4, 1L));
        hotelsList.add(new Hotel(5L, "City Lights Inn", 5, 2L));
        hotelsList.add(new Hotel(6L, "Cozy Cabin Resort", 4, 3L));
        hotelsList.add(new Hotel(7L, "Historic Grand Hotel", 3, 2L));
        hotelsList.add(new Hotel(8L, "Tranquil Gardens", 2, 1L));
        hotelsList.add(new Hotel(9L, "Riverside Long", 1, 3L));
        hotelsList.add(new Hotel(10L, "Ocean Breeze", 5, 3L));
    }
}
