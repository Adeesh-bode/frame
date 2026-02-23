package com.epam.campus.selenium.Payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// phone field was coming extra::
// By default, Jackson works in:FAIL_ON_UNKNOWN_PROPERTIES = true
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserResponse(
        int id,
        String name,
        String username,
        String email,
        Address address
) {}
