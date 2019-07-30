package com.hexad.bakery.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.bakery.constants.ApplicationConstant;
import com.hexad.bakery.exception.BakeryServiceException;
import com.hexad.bakery.laucher.ApplicationLauncher;
import com.hexad.bakery.models.Order;
import com.hexad.bakery.models.Product;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FileReader {


    public static Product[] loadProduct(String fileName, String type) {
        Product[] productList = null;
        switch (type) {
            case ApplicationConstant.JSON_EXTENSION:
                productList = readJsonProductFile(fileName);
                break;
            case ApplicationConstant.CSV_EXTENSION:
                productList = readCSVProductFile(fileName);
                break;
            default:
                throw new BakeryServiceException("Wrong Extension");
        }
        return productList;
    }

    public static List<Order> loadOrder(String fileName, String type) {
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

    private static Product[] readJsonProductFile(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(resourceFileAsUrl(fileName), Product[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Order> readJsonOrderFile(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(resourceFileAsUrl(fileName), new TypeReference<List<Order>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Product[] readCSVProductFile(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resourceFileAsUrl(fileName), Product[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static List<Order> readCSVOrderFile(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(resourceFileAsUrl(fileName), new TypeReference<List<Order>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static URL resourceFileAsUrl(String file) {
        return ApplicationLauncher.class.getClassLoader().getResource(file);
    }

    private FileReader(){

    }
}
