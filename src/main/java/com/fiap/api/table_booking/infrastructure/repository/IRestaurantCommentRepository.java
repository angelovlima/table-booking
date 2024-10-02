package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.RestaurantCommentJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRestaurantCommentRepository {
    RestaurantCommentJpaEntity save(RestaurantCommentJpaEntity comment);

    List<RestaurantCommentJpaEntity> findAll();

    void deleteById(Long id);

    Optional<RestaurantCommentJpaEntity> findById(Long id);
}
