package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantCommentApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantCommentApiRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant-comment")
public interface RestaurantCommentApi {

    @Operation(summary = "Registrar comentário de restaurante")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RestaurantCommentApiResponse create(@RequestBody CreateRestaurantCommentApiRequest request);

    @Operation(summary = "Atualizar comentário de restaurante")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    RestaurantCommentApiResponse update(@RequestBody UpdateRestaurantCommentApiRequest request);

    @Operation(summary = "Listar todos os comentários")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<RestaurantCommentApiResponse> findAllComments();

    @Operation(summary = "Excluir comentário de restaurante")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
