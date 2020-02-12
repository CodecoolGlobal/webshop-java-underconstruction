package com.codecool.shop.ajax;

import com.codecool.shop.ajax.json.FilteredProductJsonProvider;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductRequestProcessor implements RequestProcessor {

    private ProductDao productDao = ProductDaoMem.getInstance();
    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private SupplierDao supplierDao = SupplierDaoMem.getInstance();
    private FilteredProductJsonProvider jsonProvider = new FilteredProductJsonProvider();

    @Override
    public String extractJson(HttpServletRequest req) {
        String productCategoryId = req.getParameter("product_category");
        String supplierId = req.getParameter("supplier");

        if (productCategoryId == null && supplierId == null)
            return null;

        List<Product> products;
        if (productCategoryId != null) {

            if ("-1".equals(productCategoryId)) {
                products = productDao.getAll();
            } else {
                ProductCategory selectedCategory = productCategoryDao.find(Integer.parseInt(productCategoryId));
                products = productDao.getBy(selectedCategory);
            }
        } else {
            if ("-1".equals(supplierId)) {
                products = productDao.getAll();
            } else {
                Supplier supplier = supplierDao.find(Integer.parseInt(supplierId));
                products = productDao.getBy(supplier);
            }
        }
        return jsonProvider.provide(products);
    }

    @Override
    public void defaultResponse(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("productCategories", productCategoryDao.getAll());
        context.setVariable("page_path", "product/index.html");
        context.setVariable("selectedCategoryId", -1);
        context.setVariable("products", productDao.getAll());
        context.setVariable("suppliers", supplierDao.getAll());
        context.setVariable("selectedSupplierId", -1);
        engine.process("layout.html", context, resp.getWriter());
    }
}
