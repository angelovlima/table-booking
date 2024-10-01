package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.infrastructure.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    @Mock
    private IRestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    private RestaurantJpaEntity restaurantJpaEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantJpaEntity = new RestaurantJpaEntity(1L, "Almoço", "Restaurante X", "Rua Y", "Italiana", 50);
    }

    @Test
    void testCreateRestaurantSuccess() {
        when(restaurantRepository.save(any(RestaurantJpaEntity.class))).thenReturn(restaurantJpaEntity);

        RestaurantJpaEntity result = restaurantService.createRestaurant(restaurantJpaEntity);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(restaurantJpaEntity.getId());
        assertThat(result.getName()).isEqualTo(restaurantJpaEntity.getName());
        assertThat(result.getAddress()).isEqualTo(restaurantJpaEntity.getAddress());

        verify(restaurantRepository, times(1)).save(any(RestaurantJpaEntity.class));
    }

    @Test
    void testUpdateRestaurantSuccess() {
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurantJpaEntity));
        when(restaurantRepository.save(any(RestaurantJpaEntity.class))).thenReturn(restaurantJpaEntity);

        RestaurantJpaEntity updatedRestaurant = new RestaurantJpaEntity(1L, "Jantar", "Restaurante Z", "Rua Z", "Francesa", 60);
        RestaurantJpaEntity result = restaurantService.updateRestaurant(updatedRestaurant);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Restaurante Z");
        assertThat(result.getAddress()).isEqualTo("Rua Z");
        assertThat(result.getPeriod()).isEqualTo("Jantar");
        assertThat(result.getCuisine()).isEqualTo("Francesa");
        assertThat(result.getCapacity()).isEqualTo(60);

        verify(restaurantRepository, times(1)).findById(anyLong());
        verify(restaurantRepository, times(1)).save(any(RestaurantJpaEntity.class));
    }

    @Test
    void testUpdateRestaurantNotFound() {
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restaurantService.updateRestaurant(restaurantJpaEntity));

        verify(restaurantRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetAllRestaurantsSuccess() {
        RestaurantJpaEntity restaurant1 = new RestaurantJpaEntity(1L, "Almoço", "Restaurante X", "Rua Y", "Italiana", 50);
        RestaurantJpaEntity restaurant2 = new RestaurantJpaEntity(2L, "Jantar", "Restaurante Z", "Rua Z", "Francesa", 60);

        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant1, restaurant2));

        List<RestaurantJpaEntity> result = restaurantService.getAllRestaurants();

        assertThat(result).isNotNull().hasSize(2).containsExactly(restaurant1, restaurant2);

        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    void testDeleteRestaurantSuccess() {
        doNothing().when(restaurantRepository).deleteById(anyLong());

        restaurantService.deleteRestaurant(1L);

        verify(restaurantRepository, times(1)).deleteById(anyLong());
    }
}
