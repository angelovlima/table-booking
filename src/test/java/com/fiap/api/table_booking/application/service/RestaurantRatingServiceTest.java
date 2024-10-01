package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.infrastructure.RestaurantRatingJpaEntity;
import com.fiap.api.table_booking.infrastructure.repository.IRestaurantRatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantRatingServiceTest {

    @Mock
    private IRestaurantRatingRepository ratingRepository;

    @InjectMocks
    private RestaurantRatingService ratingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CreateRating {

        @Test
        void testCreateRatingSuccess() {
            RestaurantRatingJpaEntity rating = new RestaurantRatingJpaEntity(null, 5, null, null);

            when(ratingRepository.save(any(RestaurantRatingJpaEntity.class))).thenReturn(rating);

            RestaurantRatingJpaEntity savedRating = ratingService.createRating(rating);

            assertNotNull(savedRating);
            assertEquals(5, savedRating.getStars());
            verify(ratingRepository, times(1)).save(rating);
        }
    }

    @Nested
    class UpdateRating {

        @Test
        void testUpdateRatingSuccess() {
            RestaurantRatingJpaEntity rating = new RestaurantRatingJpaEntity(1L, 4, null, null);

            when(ratingRepository.findById(1L)).thenReturn(Optional.of(rating));
            when(ratingRepository.save(any(RestaurantRatingJpaEntity.class))).thenReturn(rating);

            RestaurantRatingJpaEntity updatedRating = ratingService.updateRating(rating);

            assertNotNull(updatedRating);
            assertEquals(4, updatedRating.getStars());
            verify(ratingRepository, times(1)).findById(1L);
            verify(ratingRepository, times(1)).save(rating);
        }

        @Test
        void testUpdateRatingNotFound() {
            RestaurantRatingJpaEntity rating = new RestaurantRatingJpaEntity(999L, 4, null, null);

            when(ratingRepository.findById(999L)).thenReturn(Optional.empty());

            Exception exception = assertThrows(RuntimeException.class, () -> ratingService.updateRating(rating));

            assertEquals("Avaliação não encontrada", exception.getMessage());
            verify(ratingRepository, times(1)).findById(999L);
            verify(ratingRepository, times(0)).save(any(RestaurantRatingJpaEntity.class));
        }
    }

    @Nested
    class FindRatingById {

        @Test
        void testFindRatingByIdSuccess() {
            RestaurantRatingJpaEntity rating = new RestaurantRatingJpaEntity(1L, 5, null, null);

            when(ratingRepository.findById(1L)).thenReturn(Optional.of(rating));

            RestaurantRatingJpaEntity foundRating = ratingService.findRatingById(1L);

            assertNotNull(foundRating);
            assertEquals(5, foundRating.getStars());
            verify(ratingRepository, times(1)).findById(1L);
        }

        @Test
        void testFindRatingByIdNotFound() {
            when(ratingRepository.findById(999L)).thenReturn(Optional.empty());

            Exception exception = assertThrows(RuntimeException.class, () -> ratingService.findRatingById(999L));

            assertEquals("Rating not found", exception.getMessage());
            verify(ratingRepository, times(1)).findById(999L);
        }
    }

    @Nested
    class DeleteRating {

        @Test
        void testDeleteRatingSuccess() {
            RestaurantRatingJpaEntity rating = new RestaurantRatingJpaEntity(1L, 5, null, null);

            when(ratingRepository.findById(1L)).thenReturn(Optional.of(rating));

            ratingService.deleteRating(1L);

            verify(ratingRepository, times(1)).deleteById(1L);
        }
    }
}
