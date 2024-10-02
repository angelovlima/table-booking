package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantCommentApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.infrastructure.RestaurantCommentJpaEntity;

public interface IRestaurantCommentApiMapper {
    RestaurantCommentJpaEntity toDomainEntity(CreateRestaurantCommentApiRequest request);

    RestaurantCommentJpaEntity toDomainEntity(UpdateRestaurantCommentApiRequest request);

    RestaurantCommentApiResponse toApiResponse(RestaurantCommentJpaEntity comment);
}
