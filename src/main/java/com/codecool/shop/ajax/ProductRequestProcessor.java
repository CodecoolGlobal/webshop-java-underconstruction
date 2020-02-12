package com.codecool.shop.ajax;

import com.codecool.shop.ajax.json.FilteredProductJsonProvider;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DaoDirector;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductRequestProcessor implements RequestProcessor {

    private DaoDirector daoDirector = DaoDirector.getInstance();
    private FilteredProductJsonProvider jsonProvider = new FilteredProductJsonProvider();

    @Override
    public String extractJson(HttpServletRequest req) {
        String productCategoryId = req.getParameter("product_category");
        String supplierId = req.getParameter("supplier");

        List<Product> products = null;
        if (productCategoryId != null)
            products = daoDirector.productsByProductCategory(Integer.parseInt(productCategoryId));
        else if (supplierId != null)
            products = daoDirector.productsBySupplier(Integer.parseInt(supplierId));

        return products != null ? jsonProvider.provide(products) : null;
    }

    @Override
    public void defaultResponse(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("productCategories", daoDirector.productCategories());
        context.setVariable("page_path", "product/index.html");
        context.setVariable("selectedCategoryId", -1);
        context.setVariable("products", daoDirector.products());
        context.setVariable("suppliers", daoDirector.suppliers());
        context.setVariable("selectedSupplierId", -1);
        engine.process("layout.html", context, resp.getWriter());
    }
}
