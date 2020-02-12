package com.codecool.shop.ajax.filtering;

import com.codecool.shop.model.ModelType;
import com.codecool.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ProductFilteringStrategy {

    private List<ProductFilteringOption> options = new ArrayList<>();

    public ProductFilteringStrategy(HttpServletRequest req) {
        Enumeration<String> paramNames = req.getParameterNames();
        String name, value;
        while (paramNames.hasMoreElements()) {
            name = paramNames.nextElement();
            value = req.getParameter(name);
            if (!"all".equals(value)) {
                options.add(new ProductFilteringOption(value, ModelType.getByName(name)));
            }
        }
    }

    public boolean hasOptions() {
        return !options.isEmpty();
    }

    public boolean shouldRetain(Product product) {
        return options.stream().allMatch(product::passesFilter);
    }
}
