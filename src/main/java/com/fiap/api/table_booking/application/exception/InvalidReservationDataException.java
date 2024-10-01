package com.fiap.api.table_booking.application.exception;

public class InvalidReservationDataException extends RuntimeException {
    public InvalidReservationDataException(String message) {
        super(message);
    }
}