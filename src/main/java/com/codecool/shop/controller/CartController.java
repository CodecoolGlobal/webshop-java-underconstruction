package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private TemplateEngine engine;
    private WebContext context;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.context = new WebContext(req, resp, req.getServletContext());
        this.engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());

        defaultGet(req, resp);
    }

    private void defaultGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        Order order;

        if (session != null) {
            order = (Order) session.getAttribute("order");

        }
    }
}
