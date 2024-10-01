package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateCustomerApiRequest;
import com.fiap.api.table_booking.api.model.CustomerApiResponse;
import com.fiap.api.table_booking.api.model.UpdateCustomerApiRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
public interface CustomerApi {

    @Operation(
            summary = "Registrar Cliente",
            description = "Registra um novo cliente no sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateCustomerApiRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Registro de Cliente",
                                            summary = "Exemplo de dados para registrar um cliente",
                                            value = "{ \"name\": \"Ângelo Lima\", \"contact\": \"(12) 98765-4321\" }"
                                    )
                            }
                    )
            )
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerApiResponse create(@RequestBody CreateCustomerApiRequest request);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    CustomerApiResponse update(@RequestBody UpdateCustomerApiRequest request);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CustomerApiResponse> findAllCustomers();

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
