package com.fiap.api.table_booking.utils;

import com.fiap.api.table_booking.api.model.CreateCustomerApiRequest;
import com.fiap.api.table_booking.api.model.UpdateCustomerApiRequest;
import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;

import java.util.Random;

public abstract class CustomerHelper {

    public static CustomerJpaEntity generateCustomerJpaEntity() {
        return new CustomerJpaEntity(
                1L,
                "Ângelo Lima",
                generateRandomPhoneNumber()
        );
    }

    public static CreateCustomerApiRequest generateCreateCustomerApiRequest() {
        return new CreateCustomerApiRequest(
                "Ângelo Lima",
                generateRandomPhoneNumber()
        );
    }

    public static UpdateCustomerApiRequest generateUpdateCustomerApiRequest() {
        return new UpdateCustomerApiRequest(
                1L,
                "Ângelo Lima",
                generateRandomPhoneNumber()
        );
    }

    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        int ddd = random.nextInt(90) + 10;
        int firstDigit = 9;
        int firstPart = random.nextInt(90000) + 10000;
        int secondPart = random.nextInt(9000) + 1000;
        return String.format("(%02d) %d%d-%04d", ddd, firstDigit, firstPart, secondPart);
    }
}
