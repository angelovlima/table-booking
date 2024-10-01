package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRestaurantRepository {
    RestaurantJpaEntity save(RestaurantJpaEntity restaurantJpaEntity);
    List<RestaurantJpaEntity> findAll();
    void deleteById(Long id);
    Optional<RestaurantJpaEntity> findById(Long id);
    List<RestaurantJpaEntity> findByNameContainingIgnoreCase(String name);
    List<RestaurantJpaEntity> findByAddressContainingIgnoreCase(String address);
    List<RestaurantJpaEntity> findByCuisineContainingIgnoreCase(String cuisine);
}
