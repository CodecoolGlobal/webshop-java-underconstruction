package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

public interface OrderDao {

    void handleItemChange(Order order, int productId, int quantity);

}
