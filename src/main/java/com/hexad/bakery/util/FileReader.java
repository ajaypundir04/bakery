package com.hexad.bakery.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.bakery.ApplicationLauncher;
import com.hexad.bakery.constants.ApplicationConstant;
import com.hexad.bakery.exception.BakeryServiceException;
import com.hexad.bakery.models.Order;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.URL;
import java.util.List;

public final class FileReader {

    public static List<Order> loadOrder(String fileName, String type) throws Exception {
        List<Order> orderList = null;
        switch (type) {
            case ApplicationConstant.JSON_EXTENSION:
                orderList = readJsonOrderFile(fileName);
                break;
            case ApplicationConstant.CSV_EXTENSION:
                orderList = readCSVOrderFile(fileName);
                break;
            default:
                throw new BakeryServiceException("Wrong Extension");
        }
        return orderList;
    }

    private static List<Order> readJsonOrderFile(String fileName) throws Exception {
        return readFile(fileName, file ->
                new ObjectMapper().readValue(resourceFileAsUrl(fileName), new TypeReference<List<Order>>() {
                })
        );
    }

    private static List<Order> readCSVOrderFile(String fileName) throws Exception {
        return readFile(fileName, file ->
            new ObjectMapper().readValue(resourceFileAsUrl(fileName), new TypeReference<List<Order>>() {
            })
        );
    }

    private static URL resourceFileAsUrl(String file) {
        return ApplicationLauncher.class.getClassLoader().getResource(file);
    }

    private static <R> R readFile(String path, FileConverterFunction<R> converterFunction) throws Exception {
        File file = ResourceUtils.getFile(path);
        return converterFunction.convert(file);
    }

    private FileReader(){

    }

    @FunctionalInterface
    interface FileConverterFunction<R> {

        R convert(File file) throws Exception;
    }
}
