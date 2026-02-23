package com.epam.campus.selenium.Payload;

public record PostResponse(
        int userId,
        int id,
        String title,
        String body
) {}
