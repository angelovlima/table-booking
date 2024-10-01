package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.UpdateCustomerApiRequest;
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
@Sql(scripts = {"/clean.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class CustomerControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class Update {

        @Test
        @Sql(scripts = {"/clean.sql", "/insert_customer.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testUpdateCustomerSuccess() {
            var updateCustomer = new UpdateCustomerApiRequest(1L, "Ângelo Atualizado", "(12) 98765-4321");

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(updateCustomer)
                    .when()
                    .put("/customer")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", equalTo(1))
                    .body("name", equalTo("Ângelo Atualizado"))
                    .body("contact", equalTo("(12) 98765-4321"));
        }

        @Test
        void testUpdateCustomerNotFound() {
            var updateCustomer = new UpdateCustomerApiRequest(999L, "Nome Não Encontrado", "(12) 98765-4321");

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(updateCustomer)
                    .when()
                    .put("/customer")
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("message", equalTo("Cliente não encontrado"));
        }
    }

    @Nested
    class FindAllCustomers {

        @Test
        @Sql(scripts = {"/clean.sql", "/insert_customer.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testFindAllCustomersSuccess() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/customer")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("[0].id", equalTo(1))
                    .body("[0].name", equalTo("Ângelo Lima"))
                    .body("[0].contact", equalTo("(12) 98765-4321"));
        }
    }

    @Nested
    class Delete {

        @Test
        @Sql(scripts = {"/clean.sql", "/insert_customer.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void testDeleteCustomerSuccess() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .delete("/customer/1")
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }
    }
}
