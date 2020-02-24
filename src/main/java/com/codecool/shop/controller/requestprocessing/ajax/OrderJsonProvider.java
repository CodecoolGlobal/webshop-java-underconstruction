package com.codecool.shop.controller.requestprocessing.ajax;

import com.codecool.shop.model.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrderJsonProvider {

    private Gson gson;

    public OrderJsonProvider() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new OrderFieldExclusionStratgegy());
        this.gson = gsonBuilder.create();
    }
    public String provide(Order order) {
        return gson.toJson(order);
    }
}
