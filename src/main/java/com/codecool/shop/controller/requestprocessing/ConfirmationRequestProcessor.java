package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.requestprocessing.ajax.CheckoutRequestJsonConverter;
import com.codecool.shop.controller.requestprocessing.ajax.JsonProvider;
import com.codecool.shop.controller.requestprocessing.ajax.OrderJsonProvider;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ConfirmationRequestProcessor extends AbstractRequestProcessor {

    private SessionHandler sessionHandler = new SessionHandler();
    private JsonProvider<Order> jsonProvider = new OrderJsonProvider();


    @Override
    public void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException {
        strategy.invokeMethod(req, resp, this);
    }

    @Override
    void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Order order = sessionHandler.getOrderFromSession(req);
        context.setVariable("order", order);
        context.setVariable("page_path", "confirmation/confirmation.html");
        engine.process("layout.html", context, resp.getWriter());


    }

    void requestCustomerData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Order order = sessionHandler.getOrderFromSession(req);
        String json = jsonProvider.stringify(order);
        sendJson(resp, json);

    }

}
