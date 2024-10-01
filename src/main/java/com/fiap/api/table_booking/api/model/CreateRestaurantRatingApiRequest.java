package com.fiap.api.table_booking.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Objeto para registrar avaliação do restaurante")
public record CreateRestaurantRatingApiRequest(
        @NotNull(message = "ID do restaurante é obrigatório")
        @Schema(description = "ID do restaurante", example = "1") Long restaurantId,

        @NotNull(message = "ID do cliente é obrigatório")
        @Schema(description = "ID do cliente", example = "1") Long customerId,

        @NotNull(message = "A avaliação (stars) é obrigatória")
        @Min(value = 1, message = "A avaliação mínima é 1 estrela")
        @Max(value = 5, message = "A avaliação máxima é 5 estrelas")
        @Schema(description = "Número de estrelas", example = "5") Integer stars
) {
}
