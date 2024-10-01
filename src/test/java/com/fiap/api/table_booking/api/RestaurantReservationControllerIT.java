package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.api.model.UpdateRestaurantReservationApiRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant.sql", "/clean.sql", "/clean_restaurant_reservation.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class RestaurantReservationControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class CreateReservation {

        @Test
        @Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant.sql", "/insert_customer.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testCreateReservationSuccess() {
            var createReservation = new CreateRestaurantReservationApiRequest(1L, 1L, "Almoço", LocalDate.of(2024, 12, 25));

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(createReservation)
                    .when()
                    .post("/restaurant-reservation")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("period", equalTo("Almoço"))
                    .body("reservationDate", equalTo("2024-12-25"));
        }
    }

    @Nested
    class UpdateReservation {

        @Test
        @Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant_reservation.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testUpdateReservationSuccess() {
            var updateReservation = new UpdateRestaurantReservationApiRequest(1L, 1L, 1L, "Almoço", LocalDate.of(2024, 12, 26));

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(updateReservation)
                    .when()
                    .put("/restaurant-reservation")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("period", equalTo("Almoço"))
                    .body("reservationDate", equalTo("2024-12-26"));
        }
    }

    @Nested
    class FindAllReservations {

        @Test
        @Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant_reservation.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testFindAllReservationsSuccess() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/restaurant-reservation")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("[0].restaurantName", equalTo("Restaurante Italiano"))
                    .body("[0].customerName", equalTo("Ângelo Lima"))
                    .body("[0].period", equalTo("Almoço"))
                    .body("[0].reservationDate", equalTo("2024-12-25"));
        }
    }

    @Nested
    class DeleteReservation {

        @Test
        @Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant_reservation.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testDeleteReservationSuccess() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .delete("/restaurant-reservation/1")
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }
    }
}
