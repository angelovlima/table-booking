package com.fiap.api.table_booking.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Objeto para editar reserva de restaurante")
public record UpdateRestaurantReservationApiRequest(
        @NotNull(message = "ID é obrigatório")
        @Schema(description = "ID da reserva", example = "1") Long id,

        @NotNull(message = "ID do restaurante é obrigatório")
        @Schema(description = "ID do restaurante", example = "1") Long restaurantId,

        @NotNull(message = "ID do cliente é obrigatório")
        @Schema(description = "ID do cliente", example = "1") Long customerId,

        @NotBlank(message = "Período é obrigatório")
        @Schema(description = "Período da reserva", example = "Almoço") String period,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "Data da reserva é obrigatória")
        @Schema(description = "Data da reserva", example = "2024-12-25") LocalDate reservationDate
) {
}
