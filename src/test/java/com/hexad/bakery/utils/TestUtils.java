package com.hexad.bakery.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class TestUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static URL resourceFileToUrl(String filename) {
        return TestUtils.class.getClassLoader().getResource(filename);
    }

    public static <T> T readJsonFile(String name, Class<T> cls) throws IOException {
        return objectMapper.readValue(resourceFileToUrl(name), cls);
    }
}
