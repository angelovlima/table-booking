package com.fiap.api.table_booking.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Objeto para editar cliente")
public record UpdateCustomerApiRequest(
        @NotNull(message = "ID é obrigatório")
        @Schema(description = "ID do cliente", example = "1") Long id,

        @NotBlank(message = "Nome é obrigatório")
        @Schema(description = "Nome do cliente", example = "Ângelo Lima") String name,

        @NotBlank(message = "Contato é obrigatório")
        @Schema(description = "Contato do cliente", example = "(12) 98765-1234") String contact
) {

}