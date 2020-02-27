package com.codecool.shop.controller.requestprocessing.ajax;

import com.codecool.shop.model.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrderJsonProvider implements JsonProvider<Order> {

    private Gson gson;

    public OrderJsonProvider() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new OrderFieldExclusionStrategy());
        this.gson = gsonBuilder.create();
    }

    @Override
    public String stringify(Order order) {
        return gson.toJson(order);
    }
}
