package com.codecool.shop.ajax.json;

import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class FilteredProductJsonProvider {

    private Gson gson;

    public FilteredProductJsonProvider() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ProductFieldExclusionStrategy());
        this.gson = gsonBuilder.create();
    }

    public String provide(List<Product> elements) {
        return gson.toJson(elements);
    }
}
