package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateRestaurantApiRequest;
import com.fiap.api.table_booking.utils.RestaurantHelper;
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
@Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class RestaurantControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class Create {

        @Test
        @Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testCreateRestaurant() {
            CreateRestaurantApiRequest restaurant = RestaurantHelper.generateCreateRestaurantApiRequest();

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(restaurant)
                    .when()
                    .post("/restaurant")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("name", equalTo("Restaurante X"))
                    .body("cuisine", equalTo("Italiana"));
        }
    }

    @Nested
    class Update {

        @Test
        @Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/insert_restaurant.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testUpdateRestaurant() {
            var updateRestaurant = RestaurantHelper.generateUpdateRestaurantApiRequest();

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(updateRestaurant)
                    .when()
                    .put("/restaurant")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("name", equalTo("Restaurante Atualizado"));
        }
    }

    @Nested
    class FindAllRestaurants {

        @Test
        @Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/insert_restaurant.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testFindAllRestaurants() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/restaurant")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("[0].name", equalTo("Restaurante X"))
                    .body("[0].cuisine", equalTo("Italiana"));
        }
    }

    @Nested
    class Delete {
        @Test
        @Sql(scripts = {"/clean_restaurant_comment.sql", "/clean_restaurant_rating.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/insert_restaurant.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testDeleteRestaurantSuccess() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .delete("/restaurant/1")
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }
    }
}

