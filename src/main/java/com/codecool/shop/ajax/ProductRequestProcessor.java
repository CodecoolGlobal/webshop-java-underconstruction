package com.codecool.shop.ajax;

import com.codecool.shop.ajax.json.FilteredProductJsonProvider;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductRequestProcessor implements RequestProcessor {

    private ProductDao productDao = ProductDaoMem.getInstance();
    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private FilteredProductJsonProvider jsonProvider = new FilteredProductJsonProvider();

    @Override
    public String extractJson(HttpServletRequest req) {
        String productCategoryId = req.getParameter("product_category");
        if (productCategoryId != null) {
            ProductCategory selectedCategory = productCategoryDao.find(Integer.parseInt(productCategoryId));
            List<Product> products = productDao.getBy(selectedCategory);
            return jsonProvider.provide(products);
        }
        return null;
    }

    @Override
    public void defaultResponse(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("productCategories", productCategoryDao.getAll());
        context.setVariable("page_path", "product/index.html");
        context.setVariable("selectedCategory", null);
        context.setVariable("products", productDao.getAll());
        engine.process("layout.html", context, resp.getWriter());
    }
}
