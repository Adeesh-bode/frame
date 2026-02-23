package com.epam.campus.restassured.utils;

import com.epam.campus.restassured.tests.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecUtil {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setContentType("application/json")
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }
}
