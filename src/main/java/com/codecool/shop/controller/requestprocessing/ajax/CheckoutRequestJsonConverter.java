package com.codecool.shop.controller.requestprocessing.ajax;

import com.codecool.shop.model.Customer;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class CheckoutRequestJsonConverter extends RequestJsonConverter {

    public Optional<Customer> parseCustomer(HttpServletRequest req) throws IOException {
        String customerJson = readJson(req);
        Customer customer = null;
        Gson gson = new Gson();
        try {
            customer = gson.fromJson(customerJson, Customer.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
        return customer != null ? Optional.of(customer) : Optional.empty();
    }

}
