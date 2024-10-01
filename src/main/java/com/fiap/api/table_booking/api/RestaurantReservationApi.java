package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantReservationApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantReservationApiRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant-reservation")
public interface RestaurantReservationApi {

    @Operation(summary = "Registrar Reserva de Restaurante")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RestaurantReservationApiResponse create(@RequestBody CreateRestaurantReservationApiRequest request);

    @Operation(summary = "Atualizar Reserva de Restaurante")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    RestaurantReservationApiResponse update(@RequestBody UpdateRestaurantReservationApiRequest request);

    @Operation(summary = "Listar todas as Reservas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<RestaurantReservationApiResponse> findAllReservations();

    @Operation(summary = "Excluir Reserva de Restaurante")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
