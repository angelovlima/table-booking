package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.application.exception.InvalidRatingException;
import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.infrastructure.RestaurantRatingJpaEntity;
import com.fiap.api.table_booking.infrastructure.repository.IRestaurantRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantRatingService implements IRestaurantRatingService {

    @Qualifier("jpaRestaurantRating")
    private final IRestaurantRatingRepository ratingRepository;

    @Override
    public RestaurantRatingJpaEntity createRating(RestaurantRatingJpaEntity rating) {
        validateStars(rating.getStars());
        return ratingRepository.save(rating);
    }

    @Override
    public RestaurantRatingJpaEntity updateRating(RestaurantRatingJpaEntity rating) {
        validateStars(rating.getStars());

        RestaurantRatingJpaEntity existingRating = ratingRepository.findById(rating.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada"));

        existingRating.setStars(rating.getStars());
        existingRating.setCustomer(rating.getCustomer());
        existingRating.setRestaurant(rating.getRestaurant());

        return ratingRepository.save(existingRating);
    }

    @Override
    public List<RestaurantRatingJpaEntity> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    private void validateStars(Integer stars) {
        if (stars < 1 || stars > 5) {
            throw new InvalidRatingException("A avaliação deve ser entre 1 e 5 estrelas.");
        }
    }

    public RestaurantRatingJpaEntity findRatingById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
    }
}
