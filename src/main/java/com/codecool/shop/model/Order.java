package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.LinkedList;
import java.util.List;

public class Order {

    private List<LineItem> cart;

    public Order() {
        this.cart = new LinkedList<>();
    }

    public List<LineItem> getCart() {
        return cart;
    }

    public void add(LineItem lineItem ) {
        this.cart.add(lineItem);
    }

    public void remove(int productId) {
        if (this.getLineItemBy(productId) != null) {
            this.cart.remove(this.getLineItemBy(productId));
        }
    }

    private LineItem getLineItemBy(int productId) {
        for (LineItem item : cart) {
            if (item.getProduct().getId() == productId)
                return item;
        }
        return  null;
    }
}
