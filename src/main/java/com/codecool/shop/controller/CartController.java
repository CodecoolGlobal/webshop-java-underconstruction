package com.codecool.shop.controller;


import com.codecool.shop.controller.requestprocessing.CartRequestProcessor;
import com.codecool.shop.controller.requestprocessing.IRequestProcessor;
import com.codecool.shop.controller.requestprocessing.RequestProcessingStrategy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private final IRequestProcessor requestProcessor = new CartRequestProcessor();
    private final RequestProcessingStrategy DEFAULT = RequestProcessingStrategy.DEFAULT;
    private final RequestProcessingStrategy UPDATE = RequestProcessingStrategy.UPDATE_ORDER;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestProcessor.digestRequest(req, resp, DEFAULT);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestProcessor.digestRequest(req, resp, UPDATE);
    }
}
