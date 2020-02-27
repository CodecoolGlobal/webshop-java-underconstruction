package com.codecool.shop.controller.requestprocessing.ajax;

import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class ProductJsonProvider implements JsonProvider<List<Product>> {

    private Gson gson;

    public ProductJsonProvider() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ProductFieldExclusionStrategy());
        this.gson = gsonBuilder.create();
    }

    @Override
    public String stringify(List<Product> products) {
        return gson.toJson(products);
    }
}
