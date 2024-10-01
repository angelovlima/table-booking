package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateRestaurantApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantApiRequest;
import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantApiMapper implements IRestaurantApiMapper {

    @Override
    public RestaurantJpaEntity toDomainEntity(CreateRestaurantApiRequest createRestaurantApiRequest) {
        return new RestaurantJpaEntity(null, createRestaurantApiRequest.period(), createRestaurantApiRequest.name(),
                createRestaurantApiRequest.address(), createRestaurantApiRequest.cuisine(), createRestaurantApiRequest.capacity());
    }

    @Override
    public RestaurantJpaEntity toDomainEntity(UpdateRestaurantApiRequest updateRestaurantApiRequest) {
        return new RestaurantJpaEntity(updateRestaurantApiRequest.id(), updateRestaurantApiRequest.period(),
                updateRestaurantApiRequest.name(), updateRestaurantApiRequest.address(), updateRestaurantApiRequest.cuisine(),
                updateRestaurantApiRequest.capacity());
    }

    @Override
    public RestaurantApiResponse toRestaurantApiResponse(RestaurantJpaEntity restaurant) {
        return new RestaurantApiResponse(restaurant.getId(), restaurant.getName(), restaurant.getAddress(),
                restaurant.getCuisine(), restaurant.getCapacity(), restaurant.getPeriod());
    }
}
