package com.fiap.api.table_booking.bdd;

import com.fiap.api.table_booking.api.model.*;
import com.fiap.api.table_booking.utils.RestaurantHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RestaurantStepDefinition {

    private Response response;

    private RestaurantApiResponse restaurantApiResponse;

    private String ENDPOINT_RESTAURANTES = "http://localhost:8080/restaurant";


    @Quando("cadastrar um novo restaurante")
    public RestaurantApiResponse cadastrarUmNovoRestaurante() {
        var createRestaurantApiRequest = RestaurantHelper.generateCreateRestaurantApiRequest();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createRestaurantApiRequest)
                .when()
                .post(ENDPOINT_RESTAURANTES);
        return response.then().extract().as(RestaurantApiResponse.class);
    }

    @Então("o restaurante é cadastrado com sucesso")
    public void restauranteCadastradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/RestaurantResponseSchema.json"));
    }

    @Dado("que um restaurante já foi cadastrado")
    public void restauranteJaCadastrado() {
        restaurantApiResponse = cadastrarUmNovoRestaurante();
    }

    @Quando("efetuar requisição para editar o restaurante")
    public void efetuarRequisicaoEditarRestaurante() {
        UpdateRestaurantApiRequest updateRestaurantApiRequest =
                new UpdateRestaurantApiRequest(
                        restaurantApiResponse.id(),
                        "Nome editado",
                        restaurantApiResponse.address(),
                        restaurantApiResponse.cuisine(),
                        restaurantApiResponse.capacity(),
                        restaurantApiResponse.period()
                );
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(updateRestaurantApiRequest)
                .when()
                .put(ENDPOINT_RESTAURANTES);
    }

    @Então("o restaurante é atualizado com sucesso")
    public void restauranteAtualizadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/RestaurantResponseSchema.json"));
    }

    @Quando("efetuar requisição para visualizar todos os restaurantes")
    public void efetuarRequisicaoVisualizarRestaurantes() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_RESTAURANTES);
    }

    @Então("os restaurantes devem ser apresentados com sucesso")
    public void restaurantesApresentadosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/RestaurantListResponseSchema.json"));
    }

    @Quando("efetuar requisição para excluir o restaurante")
    public void efetuarRequisicaoExcluirRestaurante() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(ENDPOINT_RESTAURANTES + "/{id}", restaurantApiResponse.id());
    }

    @Então("o restaurante é removido com sucesso")
    public void restauranteRemovidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
