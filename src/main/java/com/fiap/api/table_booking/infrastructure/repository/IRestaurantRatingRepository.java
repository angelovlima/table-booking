package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.RestaurantRatingJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRestaurantRatingRepository {
    RestaurantRatingJpaEntity save(RestaurantRatingJpaEntity rating);
    List<RestaurantRatingJpaEntity> findAll();
    void deleteById(Long id);
    Optional<RestaurantRatingJpaEntity> findById(Long id);
}
