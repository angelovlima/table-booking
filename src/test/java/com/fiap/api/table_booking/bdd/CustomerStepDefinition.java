package com.fiap.api.table_booking.bdd;

import com.fiap.api.table_booking.api.model.CustomerApiResponse;
import com.fiap.api.table_booking.api.model.UpdateCustomerApiRequest;
import com.fiap.api.table_booking.utils.CustomerHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CustomerStepDefinition {

    private Response response;

    private CustomerApiResponse customerApiResponse;

    private String ENDPOINT_CLIENTES = "http://localhost:8080/customer";

    @Quando("cadastrar um novo cliente")
    public CustomerApiResponse cadastrarUmNovoCliente() {
        var createCustomerApiRequest = CustomerHelper.generateCreateCustomerApiRequest();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createCustomerApiRequest)
                .when()
                .post(ENDPOINT_CLIENTES);
        return response.then().extract().as(CustomerApiResponse.class);
    }

    @Então("o usuário é cadastrado com sucesso")
    public void usuarioCadastradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/CustomerResponseSchema.json"));
    }

    @Dado("que um cliente já foi cadastrado")
    public void clienteJaCadastrado() {
        customerApiResponse = cadastrarUmNovoCliente();
    }

    @Quando("efetuar requisição para editar o cliente")
    public void efetuarRequisicaoEditarCliente() {
        UpdateCustomerApiRequest updateCustomerApiRequest =
                new UpdateCustomerApiRequest(
                        customerApiResponse.id(),
                        "Nome editado",
                        customerApiResponse.contact()
                );
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(updateCustomerApiRequest)
                .when()
                .put(ENDPOINT_CLIENTES);
    }

    @Então("o usuário é atualizado com sucesso")
    public void usuarioAtualizadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/CustomerResponseSchema.json"));
    }

    @Quando("efetuar requisição para visualizar todos os clientes")
    public void efetuarRequisicaoVisualizarClientes() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_CLIENTES);
    }

    @Então("os clientes devem ser apresentados com sucesso")
    public void clientesApresentadosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/CustomerListResponseSchema.json"));
    }

    @Quando("efetuar requisição para excluir o cliente")
    public void efetuarRequisicaoExcluirCliente() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(ENDPOINT_CLIENTES + "/{id}", customerApiResponse.id());
    }

    @Então("o usuário é removido com sucesso")
    public void usuarioRemovidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
