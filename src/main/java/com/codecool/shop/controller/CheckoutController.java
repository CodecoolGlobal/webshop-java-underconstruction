package com.codecool.shop.controller;

import com.codecool.shop.controller.requestprocessing.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    private final IRequestProcessor requestProcessor = new CheckoutRequestProcessor();
    private  final RequestProcessingStrategy DEFAULT = RequestProcessingStrategy.DEFAULT;
    private  final RequestProcessingStrategy ADD_CUSTOMER = RequestProcessingStrategy.ADD_CUSTOMER_TO_ORDER;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestProcessor.digestRequest(req, resp, DEFAULT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestProcessor.digestRequest(req, resp, ADD_CUSTOMER);
    }
}
