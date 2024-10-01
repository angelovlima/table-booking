package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.infrastructure.RestaurantRatingJpaEntity;

import java.util.List;

public interface IRestaurantRatingService {
    RestaurantRatingJpaEntity createRating(RestaurantRatingJpaEntity rating);
    RestaurantRatingJpaEntity updateRating(RestaurantRatingJpaEntity rating);
    List<RestaurantRatingJpaEntity> getAllRatings();
    void deleteRating(Long id);
}
