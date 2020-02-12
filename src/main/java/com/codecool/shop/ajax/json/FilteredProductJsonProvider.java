package com.codecool.shop.ajax.json;

import com.codecool.shop.model.Product;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

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
                return f.getAnnotation(Expose.class) == null;
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
