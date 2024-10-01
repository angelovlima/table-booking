package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.mapper.IRestaurantReservationApiMapper;
import com.fiap.api.table_booking.api.model.CreateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantReservationApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.application.exception.ControllerExceptionHandler;
import com.fiap.api.table_booking.application.service.IRestaurantReservationService;
import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RestaurantReservationControllerTest {

    @Mock
    private IRestaurantReservationService reservationService;

    @Mock
    private IRestaurantReservationApiMapper reservationApiMapper;

    @InjectMocks
    private RestaurantReservationController reservationController;

    AutoCloseable mock;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController)
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
    void testCreateReservation() throws Exception {
        RestaurantReservationJpaEntity reservation = new RestaurantReservationJpaEntity();
        RestaurantReservationApiResponse response = new RestaurantReservationApiResponse(
                1L, "Restaurante Italiano", "Ângelo Lima", "Almoço", LocalDate.now());

        when(reservationApiMapper.toDomainEntity(any(CreateRestaurantReservationApiRequest.class))).thenReturn(reservation);
        when(reservationService.createReservation(reservation)).thenReturn(reservation);
        when(reservationApiMapper.toApiResponse(reservation)).thenReturn(response);

        mockMvc.perform(post("/restaurant-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"restaurantId\": 1, \"customerId\": 1, \"period\": \"Almoço\", \"reservationDate\": \"2024-10-01\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.restaurantName").value("Restaurante Italiano"))
                .andExpect(jsonPath("$.customerName").value("Ângelo Lima"))
                .andExpect(jsonPath("$.period").value("Almoço"))
                .andExpect(jsonPath("$.reservationDate").value("2024-10-01"));

        verify(reservationService, times(1)).createReservation(reservation);
    }

    @Test
    void testUpdateReservation() throws Exception {
        RestaurantReservationJpaEntity reservation = new RestaurantReservationJpaEntity();
        RestaurantReservationApiResponse response = new RestaurantReservationApiResponse(
                1L, "Restaurante Italiano", "Ângelo Lima", "Jantar", LocalDate.now());

        when(reservationApiMapper.toDomainEntity(any(UpdateRestaurantReservationApiRequest.class))).thenReturn(reservation);
        when(reservationService.updateReservation(reservation)).thenReturn(reservation);
        when(reservationApiMapper.toApiResponse(reservation)).thenReturn(response);

        mockMvc.perform(put("/restaurant-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"restaurantId\": 1, \"customerId\": 1, \"period\": \"Jantar\", \"reservationDate\": \"2024-10-01\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.restaurantName").value("Restaurante Italiano"))
                .andExpect(jsonPath("$.customerName").value("Ângelo Lima"))
                .andExpect(jsonPath("$.period").value("Jantar"));

        verify(reservationService, times(1)).updateReservation(reservation);
    }

    @Test
    void testFindAllReservations() throws Exception {
        RestaurantReservationJpaEntity reservation = new RestaurantReservationJpaEntity();
        RestaurantReservationApiResponse response = new RestaurantReservationApiResponse(
                1L, "Restaurante Italiano", "Ângelo Lima", "Almoço", LocalDate.now());

        when(reservationService.getAllReservations()).thenReturn(Collections.singletonList(reservation));
        when(reservationApiMapper.toApiResponse(reservation)).thenReturn(response);

        mockMvc.perform(get("/restaurant-reservation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].restaurantName").value("Restaurante Italiano"))
                .andExpect(jsonPath("$[0].customerName").value("Ângelo Lima"))
                .andExpect(jsonPath("$[0].period").value("Almoço"));

        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    void testDeleteReservation() throws Exception {
        doNothing().when(reservationService).deleteReservation(anyLong());

        mockMvc.perform(delete("/restaurant-reservation/1"))
                .andExpect(status().isNoContent());

        verify(reservationService, times(1)).deleteReservation(anyLong());
    }
}
