package com.codecool.shop.controller;

import com.codecool.shop.controller.requestprocessing.CheckoutRequestProcessor;
import com.codecool.shop.controller.requestprocessing.RequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    private final RequestProcessor requestProcessor = new CheckoutRequestProcessor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getQueryString() != null) {
            String json = requestProcessor.extractJson(req);
            requestProcessor.sendJson(resp, json);
        } else {
            requestProcessor.defaultResponse(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestProcessor.sendJson(resp, null);
    }
}
