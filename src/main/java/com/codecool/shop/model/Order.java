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

    public int calculateTotalPrice() {
        if (cart.size() != 0){
            int totalPrice = 0;
            for (LineItem item : cart) {
                totalPrice += item.getQuantity() * item.getProduct().getRawPrice();
            }
            return totalPrice;
        }
        return 0;
    }

    public void add(LineItem lineItem ) {
        this.cart.add(lineItem);
    }

    public void removeLineItemBy(LineItem lineItem) {
        if (this.getLineItemBy(lineItem.getProduct().getId()) != null) {
            this.cart.remove(lineItem);
        }
    }

    public LineItem getLineItemBy(int productId) {
        for (LineItem item : cart) {
            if (item.getProduct().getId() == productId)
                return item;
        }
        return  null;
    }
}
