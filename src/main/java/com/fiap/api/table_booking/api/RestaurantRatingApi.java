package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateRestaurantRatingApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantRatingApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantRatingApiRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant-rating")
public interface RestaurantRatingApi {

    @Operation(summary = "Registrar avaliação de restaurante")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RestaurantRatingApiResponse create(@RequestBody CreateRestaurantRatingApiRequest request);

    @Operation(summary = "Atualizar avaliação de restaurante")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    RestaurantRatingApiResponse update(@RequestBody UpdateRestaurantRatingApiRequest request);

    @Operation(summary = "Listar todas as avaliações")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<RestaurantRatingApiResponse> findAllRatings();

    @Operation(summary = "Excluir avaliação de restaurante")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
