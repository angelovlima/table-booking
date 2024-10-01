package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;

import java.util.List;

public interface IRestaurantService {
    RestaurantJpaEntity createRestaurant(RestaurantJpaEntity restaurant);
    RestaurantJpaEntity updateRestaurant(RestaurantJpaEntity restaurant);
    List<RestaurantJpaEntity> getAllRestaurants();
    void deleteRestaurant(Long id);
    RestaurantJpaEntity getRestaurantById(Long id);
    List<RestaurantJpaEntity> findRestaurantsByName(String name);
    List<RestaurantJpaEntity> findRestaurantsByLocation(String location);
    List<RestaurantJpaEntity> findRestaurantsByCuisine(String cuisine);
}
