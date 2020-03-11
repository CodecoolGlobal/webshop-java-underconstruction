package com.codecool.shop.controller;

import com.codecool.shop.controller.requestprocessing.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/confirmation", "/confirmation/customer"})
public class ConfirmationController extends HttpServlet {

    private final IRequestProcessor requestProcessor = new ConfirmationRequestProcessor();
    private  final RequestProcessingStrategy DEFAULT = RequestProcessingStrategy.DEFAULT;
    private  final RequestProcessingStrategy REQUEST_CUSTOMER_DATA = RequestProcessingStrategy.REQUEST_CUSTOMER_DATA;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/confirmation"))
            requestProcessor.digestRequest(req, resp, DEFAULT);
        else
            requestProcessor.digestRequest(req, resp, REQUEST_CUSTOMER_DATA);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
