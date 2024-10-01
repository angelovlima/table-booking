package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaRestaurant")
public interface JpaRestaurantRepository extends IRestaurantRepository, JpaRepository<RestaurantJpaEntity, Long> {
}
