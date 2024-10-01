package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.api.model.UpdateRestaurantCommentApiRequest;
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
@Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant_comment.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class RestaurantCommentControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class CreateComment {

        @Test
        @Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant_comment.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant.sql", "/insert_customer.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testCreateCommentSuccess() {
            var createComment = new CreateRestaurantCommentApiRequest(1L, 1L, "Ótimo restaurante!");

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(createComment)
                    .when()
                    .post("/restaurant-comment")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("comment", equalTo("Ótimo restaurante!"));
        }
    }

    @Nested
    class UpdateComment {

        @Test
        @Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant_comment.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant_comment.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testUpdateCommentSuccess() {
            var updateComment = new UpdateRestaurantCommentApiRequest(1L, 1L, 1L, "Serviço excelente!");

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(updateComment)
                    .when()
                    .put("/restaurant-comment")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("comment", equalTo("Serviço excelente!"));
        }
    }

    @Nested
    class FindAllComments {

        @Test
        @Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant_comment.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant_comment.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testFindAllCommentsSuccess() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/restaurant-comment")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("[0].restaurantName", equalTo("Restaurante Italiano"))
                    .body("[0].customerName", equalTo("Ângelo Lima"))
                    .body("[0].comment", equalTo("Ótimo restaurante!"));
        }
    }

    @Nested
    class DeleteComment {

        @Test
        @Sql(scripts = {"/clean_restaurant_rating.sql", "/clean_restaurant_comment.sql", "/clean_restaurant_reservation.sql", "/clean_restaurant.sql", "/clean.sql", "/insert_restaurant_comment.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testDeleteCommentSuccess() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .delete("/restaurant-comment/1")
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }
    }
}