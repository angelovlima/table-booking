package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantCommentApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import com.fiap.api.table_booking.infrastructure.RestaurantCommentJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantCommentApiMapper implements IRestaurantCommentApiMapper {

    @Override
    public RestaurantCommentJpaEntity toDomainEntity(CreateRestaurantCommentApiRequest request) {
        return new RestaurantCommentJpaEntity(
                null,
                request.comment(),
                new RestaurantJpaEntity(request.restaurantId(), null, null, null, null, null),
                new CustomerJpaEntity(request.customerId(), null, null)
        );
    }

    @Override
    public RestaurantCommentJpaEntity toDomainEntity(UpdateRestaurantCommentApiRequest request) {
        return new RestaurantCommentJpaEntity(
                request.id(),
                request.comment(),
                new RestaurantJpaEntity(request.restaurantId(), null, null, null, null, null),
                new CustomerJpaEntity(request.customerId(), null, null)
        );
    }

    @Override
    public RestaurantCommentApiResponse toApiResponse(RestaurantCommentJpaEntity comment) {
        return new RestaurantCommentApiResponse(
                comment.getId(),
                comment.getRestaurant().getName(),
                comment.getCustomer().getName(),
                comment.getComment()
        );
    }
}
