package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaRestaurantRepositoryTest {

    @Mock
    private IRestaurantRepository restaurantRepository;
    AutoCloseable openMock;

    @BeforeEach
    void setup() {
        openMock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMock.close();
    }

    @Test
    void shouldAllowRegisterRestaurant() {
        // Arrange
        RestaurantJpaEntity restaurant = new RestaurantJpaEntity(1L, "Almoço", "Restaurante X", "Rua Y", "Italiana", 50);
        when(restaurantRepository.save(any(RestaurantJpaEntity.class))).thenReturn(restaurant);
        // Act
        RestaurantJpaEntity savedRestaurant = restaurantRepository.save(restaurant);
        // Assert
        assertThat(savedRestaurant).isNotNull().isEqualTo(restaurant);
        verify(restaurantRepository, times(1)).save(any(RestaurantJpaEntity.class));
    }

    @Test
    void shouldFindRestaurantById() {
        // Arrange
        RestaurantJpaEntity restaurant = new RestaurantJpaEntity(1L, "Almoço", "Restaurante X", "Rua Y", "Italiana", 50);
        when(restaurantRepository.findById(1L)).thenReturn(java.util.Optional.of(restaurant));
        // Act
        var foundRestaurant = restaurantRepository.findById(1L);
        // Assert
        assertThat(foundRestaurant).isPresent();
        assertThat(foundRestaurant.get()).isEqualTo(restaurant);
        verify(restaurantRepository, times(1)).findById(1L);
    }

    @Test
    void shouldDeleteRestaurantById() {
        // Act
        restaurantRepository.deleteById(1L);
        // Assert
        verify(restaurantRepository, times(1)).deleteById(1L);
    }
}
