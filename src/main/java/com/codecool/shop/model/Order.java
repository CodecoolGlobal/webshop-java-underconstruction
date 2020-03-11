package com.codecool.shop.model;

import com.google.gson.annotations.Expose;

import java.util.LinkedList;
import java.util.List;

public class Order {

    @Expose
    private List<LineItem> cart;
    @Expose
    private int itemsTotal = 0;
    @Expose
    private  float priceTotal = 0;

    private Customer customer;
    private String status;

    public Order() {
        this.cart = new LinkedList<>();
    }

    public List<LineItem> getCart() {
        return cart;
    }

    public int getItemsTotal() {
        return itemsTotal;
    }

    public float getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal() {
        float totalPrice = 0;

        if (cart.size() != 0){
            for (LineItem item : cart) {
                totalPrice += (item.getQuantity() * item.getProduct().getRawPrice());
            }
        }
        this.priceTotal = totalPrice;
    }

    public void setItemsTotal() {
        if (cart.size() != 0) {
            this.itemsTotal = cart.stream().mapToInt(LineItem::getQuantity).sum();
        }
    }

    public void add(LineItem lineItem ) {
        this.cart.add(lineItem);
    }

    public void removeLineItemBy(LineItem lineItem) {
        if (this.getLineItemBy(lineItem.getProduct().getId()) != null) {
            this.cart.remove(lineItem);
            this.itemsTotal--;
        }
    }

    public LineItem getLineItemBy(int productId) {
        for (LineItem item : cart) {
            if (item.getProduct().getId() == productId)
                return item;
        }
        return  null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
