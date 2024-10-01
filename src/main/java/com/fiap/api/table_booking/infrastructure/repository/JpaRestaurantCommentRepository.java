package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.RestaurantCommentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaRestaurantComment")
public interface JpaRestaurantCommentRepository extends IRestaurantCommentRepository, JpaRepository<RestaurantCommentJpaEntity, Long> {
}
