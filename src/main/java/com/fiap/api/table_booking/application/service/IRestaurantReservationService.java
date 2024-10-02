package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;

import java.util.List;

public interface IRestaurantReservationService {
    RestaurantReservationJpaEntity createReservation(RestaurantReservationJpaEntity reservation);

    RestaurantReservationJpaEntity updateReservation(RestaurantReservationJpaEntity reservation);

    List<RestaurantReservationJpaEntity> getAllReservations();

    void deleteReservation(Long id);
}
