package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    private final ProductDao productDao = ProductDaoMem.getInstance();
    private final ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private TemplateEngine engine;
    private WebContext context;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if ("/favicon.ico".equals(req.getRequestURI())) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        this.engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        this.context = new WebContext(req, resp, req.getServletContext());

        defaultGet(req, resp);
    }

    protected void defaultGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductCategory productCategory = productCategoryDao.find(1);
        List<ProductCategory> productCategories = productCategoryDao.getAll();

        context.setVariable("productCategories", productCategories);
        context.setVariable("selectedCategory", productCategory);
        context.setVariable("products", productDao.getBy(productCategory));
        context.setVariable("page_path", "product/index.html");

        engine.process("layout.html", context, resp.getWriter());
    }
}
