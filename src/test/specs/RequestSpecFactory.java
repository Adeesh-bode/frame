package com.epam.campus.restassured.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class RequestSpecFactory {
    private static RequestSpecification baseSpec;

    static {
        baseSpec = new RequestSpecBuilder()
                .addHeader("Accept", "application/json")
//                .log().all()
                .build();
    }

    public static RequestSpecification baseRequestSpec() {
        return given()
                .spec(baseSpec);
    }

    // For Read-Only Operations
    public static RequestSpecification getRequestSpec() {
        return given()
                .spec(baseSpec)
                .log().ifValidationFails();
    }

    // For Creating Resources
    public static RequestSpecification postRequestSpec() {
        return given()
                .spec(baseSpec)
                .contentType(ContentType.JSON)
                .log().all();   // log full request for debugging
    }

    // 🔹 For Updating Resources
    public static RequestSpecification putRequestSpec() {
        return given()
                .spec(baseSpec)
                .contentType(ContentType.JSON)
                .log().all();
    }

    // 🔹 For Deleting Resources
    public static RequestSpecification deleteRequestSpec() {
        return given()
                .spec(baseSpec)
                .log().ifValidationFails();
    }
}
