package com.codecool.shop.controller.requestprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public interface RequestProcessor {

    String extractJson(HttpServletRequest req);

    void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void manipulateDao(HttpServletRequest req);

    default void sendJson(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }

    default boolean filteredFaviconRequest(HttpServletRequest req, HttpServletResponse resp) {
        if ("/favicon.ico".equals(req.getRequestURI())) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return true;
        } else {
            return false;
        }
    }
}
