package com.codecool.shop.controller;

import com.codecool.shop.controller.requestprocessing.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends HttpServlet {

    private final IRequestProcessor requestProcessor = new ConfirmationRequestProcessor();
    private  final RequestProcessingStrategy DEFAULT = RequestProcessingStrategy.DEFAULT;
    private  final RequestProcessingStrategy SEND_EMAIL_TO_CLIENT = RequestProcessingStrategy.SEND_EMAIL_TO_CLIENT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            requestProcessor.digestRequest(req, resp, DEFAULT);
            requestProcessor.digestRequest(req, resp, SEND_EMAIL_TO_CLIENT);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
