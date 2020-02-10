package com.codecool.shop.data;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CSVLoader {

    public static BufferedReader loadData(String resourceName, ServletContext sc) {
        InputStream is = sc.getResourceAsStream(resourceName);
        return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
    }
}
