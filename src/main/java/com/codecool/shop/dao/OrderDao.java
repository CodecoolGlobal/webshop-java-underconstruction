package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;

public interface OrderDao {

    void add(LineItem lineItem);
    void remove(int lineItem);
}
