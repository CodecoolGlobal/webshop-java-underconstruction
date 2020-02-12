package com.codecool.shop.util;

import com.codecool.shop.model.Product;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class Jsonifier {

    private Gson gson;

    public Jsonifier(String modelName) {
        if ("product".equals(modelName)) {
            buildProductConverter();
        } else {
            this.gson = new Gson();
        }
    }

    private void buildProductConverter() {
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
        this.gson = gsonBuilder.create();
    }

    public String convert(List<Product> elements) {
        return gson.toJson(elements);
    }
}
