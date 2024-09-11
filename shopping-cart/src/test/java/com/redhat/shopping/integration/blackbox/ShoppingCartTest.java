package com.redhat.shopping.integration.blackbox;

import java.util.Random;

import com.redhat.shopping.cart.AddToCartCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
public class ShoppingCartTest {
    
    private int randomQuantity() {
        return new Random().nextInt(10) + 1;
    }

    private void addProductToTheCartWithIdAndRamdomQuantity(int productId) {
        AddToCartCommand productToAdd = new AddToCartCommand(productId, this.randomQuantity());

        RestAssured.given()
            .contentType("application/json")
            .body(productToAdd)
            .put("/cart");
    }

    @BeforeEach
    public void clearCart() {

        RestAssured.delete("/cart");
    }

    @Test
    public void removingNonExistingProductInCatalogReturns400() {

        RestAssured.given()
            .pathParam("id", 9999)
        .when()
            .delete("/cart/products/{id}")
        .then()
            .statusCode(400);
    }

    @Test
    public void removingNonAddedProductToTheChartReturns404() {

        RestAssured.given()
            .pathParam("id", 1)
        .when()
            .delete("/cart/products/{id}")
        .then()
            .statusCode(404);
    }

    @Test
    public void removingTheOnlyProductInCartReturns204() {

        this.addProductToTheCartWithIdAndRamdomQuantity(1);

        RestAssured.given()
            .pathParam("id", 1)
        .when()
            .delete("/cart/products/{id}")
        .then()
            .statusCode(204);
    }

    @Test
    public void removingProductFromCartContainingMultipleAndDifferentProductsReturns200() {

        this.addProductToTheCartWithIdAndRamdomQuantity(1);
        this.addProductToTheCartWithIdAndRamdomQuantity(2);

        RestAssured.given()
            .pathParam("id", 1)
        .when()
            .delete("/cart/products/{id}")
        .then()
            .statusCode(200);
    }

}
