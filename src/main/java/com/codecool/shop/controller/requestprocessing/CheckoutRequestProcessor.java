package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.requestprocessing.ajax.CheckoutRequestJsonConverter;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class CheckoutRequestProcessor extends AbstractRequestProcessor {

    private CheckoutRequestJsonConverter jsonConverter = new CheckoutRequestJsonConverter();
    private SessionHandler sessionHandler = new SessionHandler();

    @Override
    public void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException {
        switch (strategy) {
            case DEFAULT:
                defaultResponse(req, resp);
                break;
            case ADD_CUSTOMER_TO_ORDER:
                addCustomerToOrder(req, resp);
                break;
        }
    }

    @Override
    void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("page_path", "checkout/checkout.html");
        engine.process("layout.html", context, resp.getWriter());
    }

    void addCustomerToOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Customer> optionalCustomer = jsonConverter.parseCustomer(req);
        optionalCustomer.ifPresent(customer ->  {
            Order order = sessionHandler.getOrderFromSession(req);
            order.setCustomer(optionalCustomer.get());
        });
        String json = jsonConverter.parsingSuccessJson(optionalCustomer.isPresent());
        sendJson(resp, json);
    }
}
