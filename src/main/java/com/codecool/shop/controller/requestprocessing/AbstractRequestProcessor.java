package com.codecool.shop.controller.requestprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

abstract class AbstractRequestProcessor implements IRequestProcessor {

    AbstractRequestProcessor() {
    }

    abstract void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    @Override
    public boolean queryStringPresent(HttpServletRequest req) {
        return req.getQueryString() != null;
    }

    void sendJson(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }

    @Override
    public boolean filteredFaviconRequest(HttpServletRequest req, HttpServletResponse resp) {
        if ("/favicon.ico".equals(req.getRequestURI())) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return true;
        } else {
            return false;
        }
    }
}
