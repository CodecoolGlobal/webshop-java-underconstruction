package com.codecool.shop.controller;

import com.codecool.shop.controller.requestprocessing.*;
import com.codecool.shop.controller.email.client.EmailClient;

import javax.mail.MessagingException;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            requestProcessor.digestRequest(req, resp, DEFAULT);

            try {
                EmailClient.sendAsHtml("shamanshop.customer@gmail.com", "Test email", "<h2>Java Mail Example</h2><p>hi there!</p>");
            } catch (MessagingException mex) {mex.printStackTrace();}


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
