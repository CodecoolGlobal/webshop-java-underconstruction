package com.codecool.shop.ajax;

import com.codecool.shop.ajax.filtering.ProductFilteringStrategy;
import com.codecool.shop.ajax.json.FilteredProductJsonProvider;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DaoDirector;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductRequestProcessor implements RequestProcessor {

    private DaoDirector daoDirector = DaoDirector.getInstance();
    private FilteredProductJsonProvider jsonProvider = new FilteredProductJsonProvider();

    @Override
    public String extractJson(HttpServletRequest req) {
        if (req.getQueryString() == null)
            return null;

        ProductFilteringStrategy strategy = new ProductFilteringStrategy(req);
        List<Product> products = daoDirector.productsBy(strategy);
        return jsonProvider.provide(products);
    }

    @Override
    public void defaultResponse(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        SessionHandler sessionHandler = new SessionHandler();
        HttpSession session = sessionHandler.getSession(req);
        Order order = null;
        if (sessionHandler.checkOrderInSession(session).getItemsTotal() != 0) {
            order = sessionHandler.checkOrderInSession(session);
            sessionHandler.bindOrderToSession(session, order);
        }


        context.setVariable("products", daoDirector.products());
        context.setVariable("productCategories", daoDirector.productCategories());
        context.setVariable("categoryId", "all");
        context.setVariable("suppliers", daoDirector.suppliers());
        context.setVariable("supplierId", "all");
        context.setVariable("page_path", "product/index.html");
        context.setVariable("order", order);
        engine.process("layout.html", context, resp.getWriter());
    }
}
