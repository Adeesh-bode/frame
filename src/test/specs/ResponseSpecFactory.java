package com.epam.campus.restassured.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class ResponseSpecFactory {

    private static ResponseSpecification baseSpec;
    private static ResponseSpecification successSpec;
    private static ResponseSpecification createdSpec;
    private static ResponseSpecification headerSpec;
    private static ResponseSpecification getHeaderSpec;

    static {
        baseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();

        successSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        createdSpec = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();

        headerSpec = new ResponseSpecBuilder()
                .expectHeader("Content-Type", containsString("application/json"))
                .expectHeader("Cache-Control", notNullValue(String.class))
                .expectHeader("Content-Length", notNullValue(String.class))
                .build();

        getHeaderSpec = new ResponseSpecBuilder()
                .expectHeader("Content-Type", containsString("application/json"))
                .expectHeader("Cache-Control", notNullValue(String.class))
                .build();
    }

    public static ResponseSpecification baseResponseSpec() {
        return baseSpec;
    }
    public static ResponseSpecification successResponseSpec() {
        return successSpec;
    }

    public static ResponseSpecification createdResponseSpec() {
        return createdSpec;
    }

    public static ResponseSpecification responseHeaderSpec() {
        return headerSpec;
    }

    public static ResponseSpecification getResponseHeaderSpec() {
        return getHeaderSpec;
    }
}
