package com.epam.campus.selenium.Payload;

//public record Geo(
//        String lat,
//        String lng
//) {}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Geo{
    String lat;
    String lng;
}
