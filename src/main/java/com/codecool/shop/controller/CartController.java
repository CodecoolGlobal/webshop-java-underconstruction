package com.codecool.shop.controller;


import com.codecool.shop.controller.requestprocessing.CartRequestProcessor;
import com.codecool.shop.controller.requestprocessing.RequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private final RequestProcessor requestProcessor = new CartRequestProcessor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        requestProcessor.defaultResponse(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = requestProcessor.extractJson(req);
        requestProcessor.sendJson(resp, json);

    }
}
