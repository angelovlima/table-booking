package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateRestaurantApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantApiRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant")
public interface RestaurantApi {

    @Operation(
            summary = "Registrar Restaurante",
            description = "Registra um novo restaurante no sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateRestaurantApiRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Registro de Restaurante",
                                            summary = "Exemplo de dados para registrar um restaurante",
                                            value = "{ \"name\": \"Restaurante Italiano\", \"address\": \"Av. Paulista, 123\", \"cuisine\": \"Italiana\", \"capacity\": 100, \"period\": \"Almoço\" }"
                                    )
                            }
                    )
            )
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RestaurantApiResponse create(@RequestBody CreateRestaurantApiRequest request);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    RestaurantApiResponse update(@RequestBody UpdateRestaurantApiRequest request);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<RestaurantApiResponse> findAllRestaurants();

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);

    @Operation(summary = "Buscar restaurantes por nome")
    @GetMapping("/search-by-name")
    @ResponseStatus(HttpStatus.OK)
    List<RestaurantApiResponse> findRestaurantsByName(@RequestParam("name") String name);

    @Operation(summary = "Buscar restaurantes por localização")
    @GetMapping("/search-by-location")
    @ResponseStatus(HttpStatus.OK)
    List<RestaurantApiResponse> findRestaurantsByLocation(@RequestParam("location") String location);

    @Operation(summary = "Buscar restaurantes por tipo de cozinha")
    @GetMapping("/search-by-cuisine")
    @ResponseStatus(HttpStatus.OK)
    List<RestaurantApiResponse> findRestaurantsByCuisine(@RequestParam("cuisine") String cuisine);
}
