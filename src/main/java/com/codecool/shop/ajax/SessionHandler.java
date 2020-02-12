package com.codecool.shop.ajax;

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
        return null;
    }

    public void bindOrderToSession(HttpSession session, Order order) {
        session.setAttribute("order", order);
    }
}
