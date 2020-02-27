package com.codecool.shop.controller.requestprocessing.filtering;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Filterable;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

public class ProductFilteringStrategy {

    private HashMap<String, Integer> filterMap = new HashMap<>();

    public ProductFilteringStrategy(HttpServletRequest req) {

    }
}
