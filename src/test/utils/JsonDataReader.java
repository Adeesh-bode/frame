package com.epam.campus.selenium.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonDataReader {

    public static List<Map<String, Object>> readJson(String path) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                new File(path),
                new TypeReference<List<Map<String, Object>>>() {}
        );
    }
}
