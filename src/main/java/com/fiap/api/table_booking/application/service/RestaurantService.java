package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.infrastructure.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService implements IRestaurantService {

    @Qualifier("jpaRestaurant")
    private final IRestaurantRepository restaurantRepository;

    @Override
    public RestaurantJpaEntity createRestaurant(RestaurantJpaEntity restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public RestaurantJpaEntity updateRestaurant(RestaurantJpaEntity restaurant) {
        RestaurantJpaEntity existingRestaurant = restaurantRepository.findById(restaurant.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));

        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setAddress(restaurant.getAddress());
        existingRestaurant.setCuisine(restaurant.getCuisine());
        existingRestaurant.setCapacity(restaurant.getCapacity());
        existingRestaurant.setPeriod(restaurant.getPeriod());

        return restaurantRepository.save(existingRestaurant);
    }

    @Override
    public List<RestaurantJpaEntity> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantJpaEntity getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));
    }

    @Override
    public List<RestaurantJpaEntity> findRestaurantsByName(String name) {
        return restaurantRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<RestaurantJpaEntity> findRestaurantsByLocation(String location) {
        return restaurantRepository.findByAddressContainingIgnoreCase(location);
    }

    @Override
    public List<RestaurantJpaEntity> findRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findByCuisineContainingIgnoreCase(cuisine);
    }
}
