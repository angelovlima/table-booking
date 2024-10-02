package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateRestaurantRatingApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantRatingApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantRatingApiRequest;
import com.fiap.api.table_booking.infrastructure.RestaurantRatingJpaEntity;

public interface IRestaurantRatingApiMapper {
    RestaurantRatingJpaEntity toDomainEntity(CreateRestaurantRatingApiRequest request);

    RestaurantRatingJpaEntity toDomainEntity(UpdateRestaurantRatingApiRequest request);

    RestaurantRatingApiResponse toApiResponse(RestaurantRatingJpaEntity rating);
}
