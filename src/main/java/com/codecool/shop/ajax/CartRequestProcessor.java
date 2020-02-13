package com.codecool.shop.ajax;

import com.codecool.shop.ajax.json.OrderJsonProvider;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
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
        SessionHandler sessionHandler = new SessionHandler();
        OrderDao orderDao = new OrderDaoMem();
        OrderJsonProvider jsonProvider = new OrderJsonProvider();

        HttpSession session = sessionHandler.getSession(req);

        Order order = sessionHandler.checkOrderInSession(session);
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        orderDao.handleItemChange(order, productId, quantity);
        sessionHandler.bindOrderToSession(session, order);

        return jsonProvider.provide(order);
    }

    @Override
    public void defaultResponse(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        SessionHandler sessionHandler = new SessionHandler();

        HttpSession session = sessionHandler.getSession(req);
        Order order = sessionHandler.checkOrderInSession(session);

        context.setVariable("order", order);
        context.setVariable("page_path", "cart/cart.html");
        engine.process("layout.html", context, resp.getWriter());
    }
}
