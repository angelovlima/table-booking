package com.fiap.api.table_booking.application.validation;

import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;
import com.fiap.api.table_booking.application.exception.InvalidReservationDataException;
import org.springframework.stereotype.Component;

@Component
public class RestaurantReservationValidation {

    public void validateReservationData(RestaurantReservationJpaEntity reservation) {
        if (reservation.getRestaurant() == null || reservation.getRestaurant().getId() == null) {
            throw new InvalidReservationDataException("O restaurante é obrigatório.");
        }
        if (reservation.getCustomer() == null || reservation.getCustomer().getId() == null) {
            throw new InvalidReservationDataException("O cliente é obrigatório.");
        }
        if (reservation.getPeriod() == null || reservation.getPeriod().trim().isEmpty()) {
            throw new InvalidReservationDataException("O período da reserva é obrigatório.");
        }
    }
}
