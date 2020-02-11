package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Map<Integer, Integer> cart;

    public Order() {
        this.cart = new HashMap<>();
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void addProductBy(int productId) {
        this.cart.merge(productId, 1, Integer::sum);
    }

    public void removeProductBy(int productId) {
        if (this.cart.get(productId) != null && this.cart.get(productId) != 0) {
            this.cart.merge(productId, -1, Integer::sum);
        }
    }
}
