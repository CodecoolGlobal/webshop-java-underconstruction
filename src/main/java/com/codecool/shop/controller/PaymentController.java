package com.codecool.shop.controller;

import com.codecool.shop.controller.requestprocessing.IRequestProcessor;
import com.codecool.shop.controller.requestprocessing.RequestProcessingStrategy;
import com.codecool.shop.controller.requestprocessing.RequestProcessor;
import com.codecool.shop.controller.requestprocessing.PaymentRequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {
    private final IRequestProcessor requestProcessor = new PaymentRequestProcessor();
    private  final RequestProcessingStrategy DEFAULT = RequestProcessingStrategy.DEFAULT;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        requestProcessor.digestRequest(req, resp, DEFAULT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String json = ;
        requestProcessor.sendJson(resp, json);*/
    }

}
