package com.codecool.shop.ajax;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartRequestProcessor implements RequestProcessor{
    @Override
    public String extractJson(HttpServletRequest req) {
        return null;
    }

    @Override
    public void defaultResponse(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        HttpSession session = req.getSession(false);
        Order order = null;
        int totalPrice = 0;

        if (session != null) {
            order = (Order) session.getAttribute("order");
        }

        context.setVariable("total_price", order != null ? order.calculateTotalPrice() : totalPrice);
        context.setVariable("order", order);
        context.setVariable("page_path", "cart/cart.html");
        engine.process("layout.html", context, resp.getWriter());
    }
}
