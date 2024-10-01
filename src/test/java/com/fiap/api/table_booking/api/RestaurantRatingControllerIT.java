package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateRestaurantRatingApiRequest;
import com.fiap.api.table_booking.api.model.UpdateRestaurantRatingApiRequest;
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

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class RestaurantRatingControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class CreateRating {

        @Test
        @Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant.sql", "/insert_customer.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testCreateRatingSuccess() {
            var createRating = new CreateRestaurantRatingApiRequest(1L, 1L, 5);

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(createRating)
                    .when()
                    .post("/restaurant-rating")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("stars", equalTo(5));
        }
    }

    @Nested
    class UpdateRating {

        @Test
        @Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant_rating.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testUpdateRatingSuccess() {
            var updateRating = new UpdateRestaurantRatingApiRequest(1L, 1L, 1L, 4);

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(updateRating)
                    .when()
                    .put("/restaurant-rating")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("stars", equalTo(4));
        }
    }

    @Nested
    class FindAllRatings {

        @Test
        @Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant_rating.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testFindAllRatingsSuccess() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/restaurant-rating")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("[0].restaurantName", equalTo("Restaurante Italiano"))
                    .body("[0].customerName", equalTo("Ângelo Lima"))
                    .body("[0].stars", equalTo(5));
        }
    }

    @Nested
    class DeleteRating {

        @Test
        @Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant_rating.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testDeleteRatingSuccess() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .delete("/restaurant-rating/1")
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }
    }
}
