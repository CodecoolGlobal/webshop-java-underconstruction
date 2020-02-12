package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDaoMem;
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

    private void defaultGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        Order order = null;
        OrderDaoMem orderDaoMem = new OrderDaoMem();

        if (session != null) {
            order = (Order) session.getAttribute("order");
        }
        else {
            order = new Order();
            orderDaoMem.handleAddItem(order, 1);
            orderDaoMem.handleAddItem(order, 1);
            orderDaoMem.handleAddItem(order, 2);

        }

        context.setVariable("order", order);
        context.setVariable("total_price", order.calculateTotalPrice());
        context.setVariable("page_path", "cart/cart.html");
        engine.process("layout.html", context, resp.getWriter());
    }
}
