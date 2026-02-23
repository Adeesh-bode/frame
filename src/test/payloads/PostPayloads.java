package com.epam.campus.restassured.payloads;


//public class PostPayloads {
//    private String title;
//    private String body;
//    private int userId;
//
//    public PostPayload(String title, String body, int userId) {
//        this.title = title;
//        this.body = body;
//        this.userId = userId;
//    }
//}

public record PostPayloads(
        String title,
        String body,
        int userId
) {}
