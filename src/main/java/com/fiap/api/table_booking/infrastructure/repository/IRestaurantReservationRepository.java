package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IRestaurantReservationRepository {
    RestaurantReservationJpaEntity save(RestaurantReservationJpaEntity reservation);

    List<RestaurantReservationJpaEntity> findAll();

    void deleteById(Long id);

    Optional<RestaurantReservationJpaEntity> findById(Long id);

    List<RestaurantReservationJpaEntity> findAllByRestaurantIdAndReservationDate(Long restaurantId, LocalDate reservationDate);
}
