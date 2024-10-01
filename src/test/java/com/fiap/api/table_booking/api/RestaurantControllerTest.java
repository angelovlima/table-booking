package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.mapper.IRestaurantApiMapper;
import com.fiap.api.table_booking.api.model.CreateRestaurantApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantApiRequest;
import com.fiap.api.table_booking.application.exception.ControllerExceptionHandler;
import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.application.service.IRestaurantService;
import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RestaurantControllerTest {

    @Mock
    private IRestaurantService restaurantService;

    @Mock
    private IRestaurantApiMapper restaurantApiMapper;

    @InjectMocks
    private RestaurantController restaurantController;

    AutoCloseable mock;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void testCreateRestaurant() throws Exception {
        RestaurantJpaEntity restaurant = new RestaurantJpaEntity();
        RestaurantApiResponse response = new RestaurantApiResponse(1L, "Restaurante X", "Rua Y", "Italiana", 50, "Almoço");

        when(restaurantApiMapper.toDomainEntity(any(CreateRestaurantApiRequest.class))).thenReturn(restaurant);
        when(restaurantService.createRestaurant(restaurant)).thenReturn(restaurant);
        when(restaurantApiMapper.toRestaurantApiResponse(restaurant)).thenReturn(response);

        mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Restaurante X\", \"address\": \"Rua Y\", \"cuisine\": \"Italiana\", \"period\": \"Almoço\", \"capacity\": 50 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Restaurante X"))
                .andExpect(jsonPath("$.address").value("Rua Y"));

        verify(restaurantService, times(1)).createRestaurant(restaurant);
    }

    @Test
    void testUpdateRestaurant() throws Exception {
        RestaurantJpaEntity restaurant = new RestaurantJpaEntity();
        RestaurantApiResponse response = new RestaurantApiResponse(1L, "Restaurante X", "Rua Y", "Italiana", 50, "Almoço");

        when(restaurantApiMapper.toDomainEntity(any(UpdateRestaurantApiRequest.class))).thenReturn(restaurant);
        when(restaurantService.updateRestaurant(restaurant)).thenReturn(restaurant);
        when(restaurantApiMapper.toRestaurantApiResponse(restaurant)).thenReturn(response);

        mockMvc.perform(put("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Restaurante X\", \"address\": \"Rua Y\", \"cuisine\": \"Italiana\", \"period\": \"Almoço\", \"capacity\": 50 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Restaurante X"))
                .andExpect(jsonPath("$.address").value("Rua Y"));

        verify(restaurantService, times(1)).updateRestaurant(restaurant);
    }

    @Test
    void testUpdateRestaurantNotFound() throws Exception {
        UpdateRestaurantApiRequest updateRequest = new UpdateRestaurantApiRequest(999L, "Restaurante X", "Rua Y", "Italiana", 50, "Almoço");

        RestaurantJpaEntity restaurant = new RestaurantJpaEntity();
        when(restaurantApiMapper.toDomainEntity(updateRequest)).thenReturn(restaurant);
        doThrow(new ResourceNotFoundException("Restaurante não encontrado")).when(restaurantService).updateRestaurant(any(RestaurantJpaEntity.class));

        mockMvc.perform(put("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 999, \"name\": \"Restaurante X\", \"address\": \"Rua Y\", \"cuisine\": \"Italiana\", \"period\": \"Almoço\", \"capacity\": 50 }"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Restaurante não encontrado"))
                .andExpect(jsonPath("$.details[0]").exists())
                .andExpect(jsonPath("$.timestamp").exists());

        verify(restaurantService, times(1)).updateRestaurant(any(RestaurantJpaEntity.class));
    }

    @Test
    void testFindAllRestaurants() throws Exception {
        RestaurantJpaEntity restaurant = new RestaurantJpaEntity();
        RestaurantApiResponse response = new RestaurantApiResponse(1L, "Restaurante X", "Rua Y", "Italiana", 50, "Almoço");

        when(restaurantService.getAllRestaurants()).thenReturn(Collections.singletonList(restaurant));
        when(restaurantApiMapper.toRestaurantApiResponse(restaurant)).thenReturn(response);

        mockMvc.perform(get("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Restaurante X"))
                .andExpect(jsonPath("$[0].address").value("Rua Y"));

        verify(restaurantService, times(1)).getAllRestaurants();
    }

    @Test
    void testDeleteRestaurant() throws Exception {
        doNothing().when(restaurantService).deleteRestaurant(anyLong());

        mockMvc.perform(delete("/restaurant/1"))
                .andExpect(status().isNoContent());

        verify(restaurantService, times(1)).deleteRestaurant(anyLong());
    }
}
