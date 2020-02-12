package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

public interface OrderDao {

    void handleAddItem(Order order, int productId);
    void handleRemoveItem(Order order, int productId);
}
