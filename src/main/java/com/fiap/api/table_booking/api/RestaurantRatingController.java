package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.mapper.IRestaurantRatingApiMapper;
import com.fiap.api.table_booking.api.model.CreateRestaurantRatingApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantRatingApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantRatingApiRequest;
import com.fiap.api.table_booking.application.service.IRestaurantRatingService;
import com.fiap.api.table_booking.infrastructure.RestaurantRatingJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantRatingController implements RestaurantRatingApi {

    private final IRestaurantRatingService ratingService;
    private final IRestaurantRatingApiMapper ratingApiMapper;

    @Override
    public RestaurantRatingApiResponse create(CreateRestaurantRatingApiRequest request) {
        RestaurantRatingJpaEntity createdRating = ratingService.createRating(ratingApiMapper.toDomainEntity(request));
        return ratingApiMapper.toApiResponse(createdRating);
    }

    @Override
    public RestaurantRatingApiResponse update(UpdateRestaurantRatingApiRequest request) {
        RestaurantRatingJpaEntity updatedRating = ratingService.updateRating(ratingApiMapper.toDomainEntity(request));
        return ratingApiMapper.toApiResponse(updatedRating);
    }

    @Override
    public List<RestaurantRatingApiResponse> findAllRatings() {
        List<RestaurantRatingJpaEntity> ratings = ratingService.getAllRatings();
        return ratings.stream()
                .map(ratingApiMapper::toApiResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        ratingService.deleteRating(id);
    }
}
