package com.codecool.shop.controller;

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
    private final RequestProcessor requestProcessor = new PaymentRequestProcessor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        requestProcessor.defaultResponse(req, resp);
    }

}
