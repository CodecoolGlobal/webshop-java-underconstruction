package com.codecool.shop.controller.requestprocessing;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentRequestProcessor extends AbstractRequestProcessor {

    private SessionHandler sessionHandler = new SessionHandler();

    @Override
    public void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        HttpSession session = sessionHandler.getSession(req);
        Order order = sessionHandler.checkOrderInSession(session);

        context.setVariable("total_price", order.getPriceTotal());
        context.setVariable("page_path", "payment/payment.html");
        engine.process("layout.html", context, resp.getWriter());

    }


    @Override
    public void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException {
        strategy.invokeMethod(req, resp, this);
    }
}
