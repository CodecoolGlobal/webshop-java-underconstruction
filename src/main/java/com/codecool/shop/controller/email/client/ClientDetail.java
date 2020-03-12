package com.codecool.shop.controller.email.client;

import com.codecool.shop.model.Order;

public class ClientDetail {

    public ClientDetail() {
    }

    public String getRecipient(Order order) {
        return order.getCustomer().getEmail();
    }


    public String prepareEmailContent(Order order) {



        return null;
    }

}
