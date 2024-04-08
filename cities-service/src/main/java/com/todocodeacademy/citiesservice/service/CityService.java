package com.todocodeacademy.citiesservice.service;

import com.todocodeacademy.citiesservice.dto.CityDTO;
import com.todocodeacademy.citiesservice.model.City;
import com.todocodeacademy.citiesservice.repository.IHotelsAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private IHotelsAPI hotelsAPI;

    List<City> cities = new ArrayList<>();


    @Override
    @CircuitBreaker(name = "hotels-service", fallbackMethod = "fallbackGetCitiesHotel")
    @Retry(name = "hotels-service")
    public CityDTO getCitiesHotels(String name, String country) {
        City city = this.findCity(name, country);

        // Creamos el DTO de la ciudad + lista de hoteles
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCity_id(city.getCity_id());
        cityDTO.setName(city.getName());
        cityDTO.setCountry(city.getCountry());
        cityDTO.setContinent(city.getContinent());
        cityDTO.setState(city.getState());

        // Buscamos la lista de hoteles en la APi y asignamos
        cityDTO.setHotelList(hotelsAPI.getHotelsByCityId(city.getCity_id()));

        //createException();
        return cityDTO;
    }

    public City findCity(String name, String country) {
        this.loadCities();
        for (City c:cities) {
            if (c.getName().equals(name) && c.getCountry().equals(country)) {
                return c;
            }
        }
        return null;
    }

    public CityDTO fallbackGetCitiesHotel(Throwable throwable) {
        return new CityDTO(99999999999L, "Error", "Error", "Error", "Error", null);
    }

    public void createException() {
        throw new IllegalArgumentException("Prueba Resilience4j y CircuitBreaker");
    }


    public void loadCities() {
        cities.add(new City(1L, "New York City", "United States", "North America", "New York"));
        cities.add(new City(2L, "Sydney", "Australia", "Oceania", "Sydney"));
        cities.add(new City(3L, "Mumbai", "India", "Asia", "Mumbai"));
        cities.add(new City(4L, "Rio de Janeiro", "Brazil", "South America", "Rio de Janeiro"));
        cities.add(new City(5L, "Cairo", "Egypt", "Africa", "Cairo"));
        cities.add(new City(6L, "Paris", "France", "Europe", "Paris"));
        cities.add(new City(7L, "Tokyo", "Japan", "Asia", "Tokyo"));
        cities.add(new City(8L, "Toronto", "Canada", "North America", "Toronto"));
        cities.add(new City(9L, "Rome", "Italy", "Europe", "Rome"));
        cities.add(new City(10L, "Cape Town", "South Africa", "Africa", "Cape Town"));
    }




}
