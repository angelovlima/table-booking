package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantReservationApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantReservationApiMapper implements IRestaurantReservationApiMapper {

    @Override
    public RestaurantReservationJpaEntity toDomainEntity(CreateRestaurantReservationApiRequest request) {
        return new RestaurantReservationJpaEntity(
                null,
                new RestaurantJpaEntity(request.restaurantId(), null, null, null, null, null),
                new CustomerJpaEntity(request.customerId(), null, null),
                request.period(),
                request.reservationDate()
        );
    }

    @Override
    public RestaurantReservationJpaEntity toDomainEntity(UpdateRestaurantReservationApiRequest request) {
        return new RestaurantReservationJpaEntity(
                request.id(),
                new RestaurantJpaEntity(request.restaurantId(), null, null, null, null, null),
                new CustomerJpaEntity(request.customerId(), null, null),
                request.period(),
                request.reservationDate()
        );
    }

    @Override
    public RestaurantReservationApiResponse toApiResponse(RestaurantReservationJpaEntity reservation) {
        return new RestaurantReservationApiResponse(
                reservation.getId(),
                reservation.getRestaurant().getName(),
                reservation.getCustomer().getName(),
                reservation.getPeriod(),
                reservation.getReservationDate()
        );
    }
}
