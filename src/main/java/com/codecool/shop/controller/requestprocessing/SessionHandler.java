package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

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

    public User getUserFromSession(HttpServletRequest req) {
        return (User) req.getSession().getAttribute("user");
    }

    public void bindOrderToSession(HttpSession session, Order order) {
        session.setAttribute("order", order);
    }

    public void bindUserToSession(HttpServletRequest req, User user) {
        req.getSession().setAttribute("user", user);
    }

    public Integer getUserIdFromSession(HttpSession session) {
        return (Integer) session.getAttribute("userdId");
    }
}
