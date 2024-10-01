package com.fiap.api.table_booking.performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class PerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");

    ActionBuilder createCustomerRequest = http("create customer")
            .post("/customer")
            .body(StringBody("{ \"name\": \"Ângelo Lima\", \"contact\": \"(12) 98765-3333\" }"))
            .check(status().is(201))
            .check(jsonPath("$.id").saveAs("customerId"));

    ScenarioBuilder scenarioCreateCustomer = scenario("create customer")
            .exec(createCustomerRequest);

    {
        setUp(
                scenarioCreateCustomer.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                )
        )
            .protocols(httpProtocol)
            .assertions(
                    global().responseTime().max().lt(50),
                    global().failedRequests().count().is(0L));
    }
}
