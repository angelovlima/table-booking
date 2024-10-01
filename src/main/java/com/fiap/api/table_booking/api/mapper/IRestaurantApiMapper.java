package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateRestaurantApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantApiRequest;
import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;

public interface IRestaurantApiMapper {

    RestaurantJpaEntity toDomainEntity(CreateRestaurantApiRequest createRestaurantApiRequest);

    RestaurantJpaEntity toDomainEntity(UpdateRestaurantApiRequest updateRestaurantApiRequest);

    RestaurantApiResponse toRestaurantApiResponse(RestaurantJpaEntity restaurant);
}
