package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
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
    private final SupplierDao supplierDao = SupplierDaoMem.getInstance();
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

        System.out.println("selected supplier: " + req.getParameter("selected-supplier"));
        // mikor betöltöm az oldalt és még nem filtereztem és submitoltam, akkor még nem létezik a req object és azért lesz null?
        if (req.getParameter("selected-supplier") == null || req.getParameter("selected-supplier").equals("No filter")) // nem létezik még Or No filter
            defaultGet(req, resp);
        else filterBySupplier(req, resp);
    }

    protected void defaultGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductCategory productCategory = productCategoryDao.find(1);

        List<Supplier> suppliers = supplierDao.getAll();
        context.setVariable("suppliers", suppliers);


        context.setVariable("category", productCategory);
        context.setVariable("products", productDao.getBy(productCategory));
        context.setVariable("page_path", "product/index.html");

        engine.process("layout.html", context, resp.getWriter());
    }

    protected void filterBySupplier(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int selectedSupplierId = Integer.parseInt(req.getParameter("selected-supplier"));
        context.setVariable("selectedSupplierId", selectedSupplierId);
        context.setVariable("products", productDao.getBy(supplierDao.find(selectedSupplierId)));

        // refactor
        List<Supplier> suppliers = supplierDao.getAll();
        context.setVariable("suppliers", suppliers);
        ProductCategory productCategory = productCategoryDao.find(1);
        context.setVariable("category", productCategory);
        context.setVariable("page_path", "product/index.html");
        engine.process("layout.html", context, resp.getWriter());


        System.out.println("It is filtering");

    }


}
