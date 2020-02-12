package com.codecool.shop.ajax;

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
    public void defaultResponse(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("page_path", "checkout/checkout.html");
        engine.process("layout.html", context, resp.getWriter());
    }
}
