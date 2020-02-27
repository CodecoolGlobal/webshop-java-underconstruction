package com.codecool.shop.controller.requestprocessing.filtering;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;

public class ProductFilteringStrategy {

    private HashMap<String, int[]> filterMap = new HashMap<>();

    public ProductFilteringStrategy(HttpServletRequest req) {
        req.getParameterMap().forEach((key, value) -> {
                    if ("undefined".equals(value[0]))
                        return;
                    int[] ids = new int[value.length];
                    for (int i = 0; i < value.length; i++) {
                        ids[i] = Integer.parseInt(value[i]);
                    }
                    filterMap.put(key, ids);
                }
        );
    }

    public HashMap<String, int[]> getFilterMap() {
        return filterMap;
    }
}
