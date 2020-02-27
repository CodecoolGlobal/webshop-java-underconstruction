package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.requestprocessing.ajax.JsonProvider;
import com.codecool.shop.controller.requestprocessing.ajax.ProductJsonProvider;
import com.codecool.shop.controller.requestprocessing.filtering.ProductFilteringStrategy;
import com.codecool.shop.dao.DaoDirector;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductRequestProcessor extends AbstractRequestProcessor {

    private DaoDirector daoDirector = DaoDirector.getInstance();
    private JsonProvider<List<Product>> jsonProvider = new ProductJsonProvider();

    @Override
    public void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException {
        strategy.invokeMethod(req, resp, this);
    }

    private void filterProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String categoryId = req.getParameter("product_category");
        String supplierId = req.getParameter("supplier");

        ProductCategory category = null;
        Supplier supplier = null;
        if (categoryId != null)
            category = daoDirector.getProductCategoryDao().find(Integer.parseInt(categoryId));
        if (supplierId != null)
            supplier = daoDirector.getSupplierDao().find(Integer.parseInt(supplierId));

        List<Product> products = daoDirector.getProductDao().getBy(supplier, category);

        String json = jsonProvider.stringify(products);
        sendJson(resp, json);
    }

    @Override
    void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
