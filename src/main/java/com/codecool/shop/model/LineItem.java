package com.codecool.shop.model;

import com.google.gson.annotations.Expose;

public class LineItem {

    @Expose
    private Product product;
    @Expose
    private int quantity;

    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
