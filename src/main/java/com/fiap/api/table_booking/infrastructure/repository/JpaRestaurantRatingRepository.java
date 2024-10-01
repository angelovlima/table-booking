package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.RestaurantRatingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaRestaurantRating")
public interface JpaRestaurantRatingRepository extends IRestaurantRatingRepository, JpaRepository<RestaurantRatingJpaEntity, Long> {
}
