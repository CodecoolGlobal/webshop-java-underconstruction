package com.codecool.shop.controller.requestprocessing;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentRequestProcessor implements RequestProcessor {


    @Override
    public String extractJson(HttpServletRequest req) {
        return null;
    }

    @Override
    public void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        SessionHandler sessionHandler = new SessionHandler();

        HttpSession session = sessionHandler.getSession(req);
        Order order = sessionHandler.checkOrderInSession(session);



    }

    @Override
    public void manipulateDao(HttpServletRequest req) {

    }
}
