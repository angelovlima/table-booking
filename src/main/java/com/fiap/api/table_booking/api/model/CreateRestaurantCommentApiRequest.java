package com.fiap.api.table_booking.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Objeto para registrar um comentário do restaurante")
public record CreateRestaurantCommentApiRequest(
        @NotNull(message = "ID do restaurante é obrigatório")
        @Schema(description = "ID do restaurante", example = "1") Long restaurantId,

        @NotNull(message = "ID do cliente é obrigatório")
        @Schema(description = "ID do cliente", example = "1") Long customerId,

        @NotBlank(message = "Comentário é obrigatório")
        @Schema(description = "Comentário sobre o restaurante", example = "Ótimo atendimento e comida deliciosa.") String comment
) {
}
