package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("jpaRestaurantReservation")
public interface JpaRestaurantReservationRepository extends IRestaurantReservationRepository, JpaRepository<RestaurantReservationJpaEntity, Long> {

    List<RestaurantReservationJpaEntity> findAllByRestaurantIdAndReservationDate(Long restaurantId, LocalDate reservationDate);
}
