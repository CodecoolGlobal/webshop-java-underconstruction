package com.codecool.shop.controller;

import com.codecool.shop.ajax.ProductRequestProcessor;
import com.codecool.shop.ajax.RequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    private final RequestProcessor requestProcessor = new ProductRequestProcessor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (requestProcessor.filteredFaviconRequest(req, resp))
            return;

        String json = requestProcessor.extractJson(req);
        if (requestProcessor.hasExtracted(json))
            requestProcessor.sendJson(resp, json);
        else
            requestProcessor.defaultResponse(resp, req);
    }
}
