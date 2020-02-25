package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHandler {

    public HttpSession getSession(HttpServletRequest req) {
        return req.getSession();
    }

    public Order checkOrderInSession(HttpSession session) {
        if (session.getAttribute("order") != null) {
            return (Order) session.getAttribute("order");
        }
        return new Order();
    }

    public Order getOrderFromSession(HttpServletRequest req) {
        return checkOrderInSession(req.getSession());
    }

    public void bindOrderToSession(HttpSession session, Order order) {
        session.setAttribute("order", order);
    }
}
