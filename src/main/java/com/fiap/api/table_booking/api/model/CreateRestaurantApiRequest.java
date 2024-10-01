package com.fiap.api.table_booking.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Objeto para registrar restaurante")
public record CreateRestaurantApiRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Schema(description = "Nome do restaurante", example = "Restaurante Italiano") String name,

        @NotBlank(message = "Endereço é obrigatório")
        @Schema(description = "Endereço do restaurante", example = "Av. Paulista, 123") String address,

        @NotBlank(message = "Culinária é obrigatória")
        @Schema(description = "Tipo de culinária", example = "Italiana") String cuisine,

        @NotNull(message = "Capacidade é obrigatória")
        @Schema(description = "Capacidade máxima do restaurante", example = "100") Integer capacity,

        @NotBlank(message = "Período é obrigatório")
        @Schema(description = "Período de funcionamento", example = "Almoço") String period
) {
}