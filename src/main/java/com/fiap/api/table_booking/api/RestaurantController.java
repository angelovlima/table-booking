package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.mapper.IRestaurantApiMapper;
import com.fiap.api.table_booking.api.model.CreateRestaurantApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantApiRequest;
import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import com.fiap.api.table_booking.application.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController implements RestaurantApi {

    private final IRestaurantService restaurantService;
    private final IRestaurantApiMapper restaurantApiMapper;

    @Override
    public RestaurantApiResponse create(CreateRestaurantApiRequest request) {
        RestaurantJpaEntity createdRestaurant = restaurantService.createRestaurant(restaurantApiMapper.toDomainEntity(request));
        return restaurantApiMapper.toRestaurantApiResponse(createdRestaurant);
    }

    @Override
    public RestaurantApiResponse update(UpdateRestaurantApiRequest request) {
        RestaurantJpaEntity updatedRestaurant = restaurantService.updateRestaurant(restaurantApiMapper.toDomainEntity(request));
        return restaurantApiMapper.toRestaurantApiResponse(updatedRestaurant);
    }

    @Override
    public List<RestaurantApiResponse> findAllRestaurants() {
        List<RestaurantJpaEntity> allRestaurants = restaurantService.getAllRestaurants();
        return allRestaurants.stream()
                .map(restaurantApiMapper::toRestaurantApiResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        restaurantService.deleteRestaurant(id);
    }

    @Override
    public List<RestaurantApiResponse> findRestaurantsByName(String name) {
        List<RestaurantJpaEntity> restaurants = restaurantService.findRestaurantsByName(name);
        return restaurants.stream()
                .map(restaurantApiMapper::toRestaurantApiResponse)
                .toList();
    }

    @Override
    public List<RestaurantApiResponse> findRestaurantsByLocation(String location) {
        List<RestaurantJpaEntity> restaurants = restaurantService.findRestaurantsByLocation(location);
        return restaurants.stream()
                .map(restaurantApiMapper::toRestaurantApiResponse)
                .toList();
    }

    @Override
    public List<RestaurantApiResponse> findRestaurantsByCuisine(String cuisine) {
        List<RestaurantJpaEntity> restaurants = restaurantService.findRestaurantsByCuisine(cuisine);
        return restaurants.stream()
                .map(restaurantApiMapper::toRestaurantApiResponse)
                .toList();
    }
}
