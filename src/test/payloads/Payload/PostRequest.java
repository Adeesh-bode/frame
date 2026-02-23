package com.epam.campus.selenium.Payload;

public record PostRequest(
        String title,
        String body,
        int userId
) {}
