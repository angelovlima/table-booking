package com.fiap.api.table_booking.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto para listar avaliações de restaurantes")
public record RestaurantRatingApiResponse(
        @Schema(description = "ID da avaliação", example = "1") Long id,
        @Schema(description = "Nome do restaurante", example = "Restaurante Italiano") String restaurantName,
        @Schema(description = "Nome do cliente", example = "Ângelo Lima") String customerName,
        @Schema(description = "Número de estrelas", example = "5") Integer stars
) {
}
