package com.epam.campus.selenium.Payload;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {}
