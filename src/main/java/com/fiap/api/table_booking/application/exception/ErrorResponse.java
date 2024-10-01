package com.fiap.api.table_booking.application.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        String message,
        List<String> details,
        LocalDateTime timestamp
) {
}
