package com.epam.campus.restassured.tests;

import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    @BeforeSuite(alwaysRun = true) // to avoid issue of grouping........
    public void globalSetup() {

        RestAssured.baseURI = BASE_URI;
//                        .time(lessThan(2000L));
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterClass
    public void tearDown() {
        // Cleanup logic (if required in future)
    }



}
