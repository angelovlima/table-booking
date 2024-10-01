package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantReservationApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;

public interface IRestaurantReservationApiMapper {
    RestaurantReservationJpaEntity toDomainEntity(CreateRestaurantReservationApiRequest request);
    RestaurantReservationJpaEntity toDomainEntity(UpdateRestaurantReservationApiRequest request);
    RestaurantReservationApiResponse toApiResponse(RestaurantReservationJpaEntity reservation);
}
