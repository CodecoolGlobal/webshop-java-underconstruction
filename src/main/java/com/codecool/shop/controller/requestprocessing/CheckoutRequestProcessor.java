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

public class CheckoutRequestProcessor implements RequestProcessor {

    private CheckoutRequestJsonConverter jsonConverter = new CheckoutRequestJsonConverter();

    @Override
    public String extractJson(HttpServletRequest req) {
        return null;
    }

    @Override
    public void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("page_path", "checkout/checkout.html");
        engine.process("layout.html", context, resp.getWriter());
    }

    @Override
    public void manipulateDao(HttpServletRequest req) {

    }

    public String addCustomerToOrder(HttpServletRequest req, SessionHandler sessionHandler) throws IOException {
        Optional<Customer> optionalCustomer = jsonConverter.parseCustomer(req);
        optionalCustomer.ifPresent(customer ->  {
            Order order = sessionHandler.getOrderFromSession(req);
            order.setCustomer(optionalCustomer.get());
        });
        return jsonConverter.parsingSuccessJson(optionalCustomer.isPresent());
    }
}
