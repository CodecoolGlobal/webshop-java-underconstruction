package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
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

        setDefaults(req, resp);

        String productCategoryId = req.getParameter("product_category");

        if (productCategoryId == null || "all".equals(productCategoryId))
            applyNoFilter();
        else
            applyCategoryFilter(productCategoryId);

        engine.process("layout.html", context, resp.getWriter());
    }

    private void setDefaults(HttpServletRequest req, HttpServletResponse resp) {
        this.engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        this.context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("productCategories", productCategoryDao.getAll());
        context.setVariable("page_path", "product/index.html");
    }

    protected void applyNoFilter() {
        setSelectedCategory(null);
        setProducts(productDao.getAll());
    }

    private void applyCategoryFilter(String productCategoryId) {
        ProductCategory selectedCategory = productCategoryDao.find(Integer.parseInt(productCategoryId));
        setSelectedCategory(selectedCategory);
        setProducts(productDao.getBy(selectedCategory));
    }

    private void setSelectedCategory(ProductCategory selectedCategory) {
        context.setVariable("selectedCategory", selectedCategory);
    }

    private void setProducts(List<Product> products) {
        context.setVariable("products", products);
    }
}
