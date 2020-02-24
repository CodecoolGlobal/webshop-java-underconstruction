package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckoutRequestProcessor implements RequestProcessor {

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
}
