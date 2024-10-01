package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateRestaurantRatingApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantRatingApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantRatingApiRequest;
import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import com.fiap.api.table_booking.infrastructure.RestaurantRatingJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantRatingApiMapper implements IRestaurantRatingApiMapper {

    @Override
    public RestaurantRatingJpaEntity toDomainEntity(CreateRestaurantRatingApiRequest request) {
        return new RestaurantRatingJpaEntity(
                null,
                request.stars(),
                new RestaurantJpaEntity(request.restaurantId(), null, null, null, null, null),
                new CustomerJpaEntity(request.customerId(), null, null)
        );
    }

    @Override
    public RestaurantRatingJpaEntity toDomainEntity(UpdateRestaurantRatingApiRequest request) {
        return new RestaurantRatingJpaEntity(
                request.id(),
                request.stars(),
                new RestaurantJpaEntity(request.restaurantId(), null, null, null, null, null),
                new CustomerJpaEntity(request.customerId(), null, null)
        );
    }

    @Override
    public RestaurantRatingApiResponse toApiResponse(RestaurantRatingJpaEntity rating) {
        return new RestaurantRatingApiResponse(
                rating.getId(),
                rating.getRestaurant().getName(),
                rating.getCustomer().getName(),
                rating.getStars()
        );
    }
}
