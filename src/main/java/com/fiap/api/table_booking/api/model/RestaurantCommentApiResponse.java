package com.fiap.api.table_booking.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto para listar comentários do restaurante")
public record RestaurantCommentApiResponse(
        @Schema(description = "ID do comentário", example = "1") Long id,
        @Schema(description = "Nome do restaurante", example = "Restaurante Italiano") String restaurantName,
        @Schema(description = "Nome do cliente", example = "Ângelo Lima") String customerName,
        @Schema(description = "Comentário sobre o restaurante", example = "Ótimo atendimento e comida deliciosa.") String comment
) {
}
