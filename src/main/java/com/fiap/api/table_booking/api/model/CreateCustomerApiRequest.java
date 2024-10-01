package com.fiap.api.table_booking.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Objeto para registrar cliente")
public record CreateCustomerApiRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Schema(description = "Nome do cliente", example = "Ângelo Lima") String name,

        @NotBlank(message = "Contato é obrigatório")
        @Schema(description = "Contato do cliente", example = "(12) 98765-4321") String contact
) {

}