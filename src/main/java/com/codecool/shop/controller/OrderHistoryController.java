package com.codecool.shop.controller;

import com.codecool.shop.controller.requestprocessing.IRequestProcessor;
import com.codecool.shop.controller.requestprocessing.OrderHistoryRequestProcessor;
import com.codecool.shop.controller.requestprocessing.RequestProcessingStrategy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/order-history"})
public class OrderHistoryController extends HttpServlet {

    private final IRequestProcessor requestProcessor = new OrderHistoryRequestProcessor();
    private final RequestProcessingStrategy DEFAULT = RequestProcessingStrategy.DEFAULT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestProcessor.digestRequest(req, resp, DEFAULT);
    }
}
