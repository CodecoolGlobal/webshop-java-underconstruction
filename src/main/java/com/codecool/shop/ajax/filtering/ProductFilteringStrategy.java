package com.codecool.shop.ajax.filtering;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Filterable;
import com.codecool.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ProductFilteringStrategy {

    private HashMap<String, Integer> filterMap = new HashMap<>();

    public ProductFilteringStrategy(HttpServletRequest req) {
        Enumeration<String> paramNames = req.getParameterNames();
        String name, value;
        while (paramNames.hasMoreElements()) {
            name = paramNames.nextElement();
            value = req.getParameter(name);
            if (!"all".equals(value)) {
                filterMap.put(name, Integer.parseInt(value));
            }
        }
    }

    public boolean shouldProcess() {
        return !filterMap.isEmpty();
    }

    public boolean process(Filterable product) {
        return filterMap.entrySet().stream().allMatch(entry -> {
            BaseModel baseModel = product.getFilterFieldMap().get(entry.getKey());
            return baseModel.getId() == entry.getValue();
        });
    }
}
