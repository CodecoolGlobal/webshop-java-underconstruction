package com.codecool.shop.ajax.json;

import com.codecool.shop.model.Product;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class FilteredProductJsonProvider {

    private Gson gson;

    public FilteredProductJsonProvider() {
        this.gson = buildProductConverter();
    }

    private Gson buildProductConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return "products".equals(f.getName());
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        });
        return gsonBuilder.create();
    }

    public String provide(List<Product> elements) {
        return gson.toJson(elements);
    }
}
