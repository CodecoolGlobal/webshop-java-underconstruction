package com.codecool.shop.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public interface RequestProcessor {

    String extractJson(HttpServletRequest req);

    default boolean hasExtracted(String json) {
        return json != null;
    }

    default void sendJson(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }

    void defaultResponse(HttpServletResponse resp, HttpServletRequest req) throws IOException;
}
