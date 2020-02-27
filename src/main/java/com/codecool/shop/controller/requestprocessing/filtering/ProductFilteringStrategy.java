package com.codecool.shop.controller.requestprocessing.filtering;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;

public class ProductFilteringStrategy {

    private HashMap<String, Integer[]> filterMap = new HashMap<>();

    public ProductFilteringStrategy(HttpServletRequest req) {
        req.getParameterMap().forEach((key, value) -> filterMap.put(
                key,
                (Integer[]) Arrays.stream(value).map(Integer::parseInt).toArray()
        ));
    }

    public HashMap<String, Integer[]> getFilterMap() {
        return filterMap;
    }
}
