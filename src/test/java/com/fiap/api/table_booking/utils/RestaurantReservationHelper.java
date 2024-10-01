package com.fiap.api.table_booking.utils;

import com.fiap.api.table_booking.api.model.CreateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.api.model.UpdateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;
import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;
import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Random;

public abstract class RestaurantReservationHelper {

    public static RestaurantReservationJpaEntity generateRestaurantReservationJpaEntity() {
        RestaurantJpaEntity restaurant = new RestaurantJpaEntity(1L, "Almoço", "Restaurante Italiano", "Rua das Flores", "Italiana", 50);
        CustomerJpaEntity customer = new CustomerJpaEntity(1L, "Ângelo Lima", generateRandomPhoneNumber());

        return new RestaurantReservationJpaEntity(
                1L,
                restaurant,
                customer,
                "Almoço",
                LocalDate.of(2024, 12, 25)
        );
    }

    public static CreateRestaurantReservationApiRequest generateCreateReservationApiRequest() {
        return new CreateRestaurantReservationApiRequest(
                1L,
                1L,
                "Almoço",
                LocalDate.of(2024, 12, 25)
        );
    }

    public RestaurantReservationJpaEntity generateRestaurantReservationJpaEntity(RestaurantJpaEntity restaurant, CustomerJpaEntity customer) {
        return new RestaurantReservationJpaEntity(
                1L,
                restaurant,
                customer,
                "Almoço",
                LocalDate.of(2024, 12, 25)
        );
    }

    public static UpdateRestaurantReservationApiRequest generateUpdateReservationApiRequest() {
        return new UpdateRestaurantReservationApiRequest(
                1L,
                1L,
                1L,
                "Jantar",
                LocalDate.of(2024, 12, 25)
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
