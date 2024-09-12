package com.redhat.training;

import com.redhat.training.service.SolverService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.mockito.Mockito.when;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import static org.mockito.ArgumentMatchers.anyString;

@QuarkusTest
@TestHTTPEndpoint(AdderResource.class)
public class AdderResourceTest {

    @InjectMock
    @RestClient 
    SolverService solverService;

    @Test
    public void testAdditionOfPositiveValues() {

        final String val1 = "2";
        final String val2 = "3";

        when(solverService.solve(anyString()))
            .thenAnswer(invocation -> Float.valueOf(invocation.getArgument(0)));

        given()
            .pathParam("lhs", val1)
            .pathParam("rhs", val2)
        .when()
            .get("/{lhs}/{rhs}")
        .then()
            .statusCode(200)
            .body(is("5.0"));        
    }
    
    @Test
    public void testAdditionOfPositiveAndNegativeValues() {

        final String val1 = "2";
        final String val2 = "-3";

        when(solverService.solve(anyString()))
            .thenAnswer(invocation -> Float.valueOf(invocation.getArgument(0)));

        given()
            .pathParam("lhs", val1)
            .pathParam("rhs", val2)
        .when()
            .get("/{lhs}/{rhs}")
        .then()
            .statusCode(200)
            .body(is("-1.0"));        
    }
    
    @Test
    public void testAdditionOfInvalidValues() {

        final String val1 = "a";
        final String val2 = "-b";

        when(solverService.solve(anyString()))
            .thenThrow(new ResteasyWebApplicationException(new WebApplicationException("invalid-value", Status.BAD_REQUEST)));

        given()
            .pathParam("lhs", val1)
            .pathParam("rhs", val2)
        .when()
            .get("/{lhs}/{rhs}")
        .then()
            .statusCode(400);
            // .body(containsString("invalid-value"));        
    }

}
