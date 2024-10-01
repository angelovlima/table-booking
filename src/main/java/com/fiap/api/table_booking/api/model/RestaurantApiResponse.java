package com.fiap.api.table_booking.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto para lista de todos os restaurantes")
public record RestaurantApiResponse(
        @Schema(description = "ID do restaurante", example = "1") Long id,
        @Schema(description = "Nome do restaurante", example = "Restaurante Italiano") String name,
        @Schema(description = "Endereço do restaurante", example = "Av. Paulista, 123") String address,
        @Schema(description = "Tipo de culinária", example = "Italiana") String cuisine,
        @Schema(description = "Capacidade máxima do restaurante", example = "100") Integer capacity,
        @Schema(description = "Período de funcionamento", example = "Almoço") String period
) {
}
