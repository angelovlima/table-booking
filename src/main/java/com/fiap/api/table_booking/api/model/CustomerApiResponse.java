package com.fiap.api.table_booking.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto para lista de todos os clientes")
public record CustomerApiResponse(
        @Schema(description = "ID do cliente", example = "1") Long id,
        @Schema(description = "Nome do cliente", example = "Ângelo Lima") String name,
        @Schema(description = "Contato do cliente", example = "(12) 98765-1234") String contact
) {

}