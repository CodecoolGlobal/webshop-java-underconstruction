package com.codecool.shop.controller.requestprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRequestProcessor extends AbstractRequestProcessor {

    @Override
    public void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException {
        strategy.invokeMethod(req, resp, this);
    }

    @Override
    void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
