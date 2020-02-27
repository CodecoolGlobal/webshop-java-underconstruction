package com.codecool.shop.controller;

import com.codecool.shop.controller.requestprocessing.IRequestProcessor;
import com.codecool.shop.controller.requestprocessing.ProductRequestProcessor;
import com.codecool.shop.controller.requestprocessing.RequestProcessingStrategy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    private final IRequestProcessor requestProcessor = new ProductRequestProcessor();
    private final RequestProcessingStrategy DEFAULT = RequestProcessingStrategy.DEFAULT;
    private final RequestProcessingStrategy FILTER = RequestProcessingStrategy.FILTER_PRODUCTS;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession();

        if (requestProcessor.filteredFaviconRequest(req, resp))
            return;

        RequestProcessingStrategy strategy = requestProcessor.queryStringPresent(req) ? FILTER : DEFAULT;
        requestProcessor.digestRequest(req, resp, strategy);
    }
}
