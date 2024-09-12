package com.redhat.training;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(SolverResource.class)
public class SolverResourceTest {

    @Test
    public void testAdditionOfPositiveValues() {

        given()
            .pathParam("equation", "2+3")
        .when()
            .get("/{equation}")
        .then()
            .statusCode(200)
            .body(is("5.0"));        
    }
    
    @Test
    public void testMultiplicationOfPositiveAndNegativeValues() {

        given()
            .pathParam("equation", "2*-3")
        .when()
            .get("/{equation}")
        .then()
            .statusCode(200)
            .body(is("-6.0"));        
    }
    
    @Test
    public void testInvalidValues() {

        given()
            .pathParam("equation", "a#b")
        .when()
            .get("/{equation}")
        .then()
            .statusCode(400);
            // .body(containsString("invalid-value"));        
    }

}
