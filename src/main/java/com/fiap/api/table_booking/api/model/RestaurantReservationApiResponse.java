package com.fiap.api.table_booking.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Objeto para listar detalhes da reserva de restaurante")
public record RestaurantReservationApiResponse(
        @Schema(description = "ID da reserva", example = "1") Long id,
        @Schema(description = "Nome do restaurante", example = "Restaurante Italiano") String restaurantName,
        @Schema(description = "Nome do cliente", example = "Ângelo Lima") String customerName,
        @Schema(description = "Período da reserva", example = "Almoço") String period,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @Schema(description = "Data da reserva", example = "2024-12-25") LocalDate reservationDate
) {
}
