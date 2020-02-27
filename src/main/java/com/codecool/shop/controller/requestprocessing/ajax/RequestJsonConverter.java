package com.codecool.shop.controller.requestprocessing.ajax;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public abstract class RequestJsonConverter {

    public String readJson(HttpServletRequest req) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        return jsonBuilder.toString();
    }

    public String parsingSuccessJson(boolean success) {
        return String.format("{\"result\":%b}", success);
    }
}
