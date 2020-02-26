package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.requestprocessing.ajax.JsonProvider;
import com.codecool.shop.controller.requestprocessing.ajax.OrderJsonProvider;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartRequestProcessor extends AbstractRequestProcessor {

    private JsonProvider<Order> jsonProvider = new OrderJsonProvider();

    @Override
    public void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException {
        strategy.invokeMethod(req, resp, this);
    }

    private void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SessionHandler sessionHandler = new SessionHandler();
        OrderDao orderDao = new OrderDaoMem();

        HttpSession session = sessionHandler.getSession(req);

        Order order = sessionHandler.checkOrderInSession(session);
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        orderDao.handleItemChange(order, productId, quantity);
        sessionHandler.bindOrderToSession(session, order);

        String json = jsonProvider.stringify(order);
        sendJson(resp, json);
    }

    @Override
    public void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
