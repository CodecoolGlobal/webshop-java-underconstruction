package com.codecool.shop.dao;

import com.codecool.shop.ajax.ProductFilteringOptions;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class DaoDirector {

    private static DaoDirector instance = new DaoDirector();

    private ProductDao productDao = ProductDaoMem.getInstance();
    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private SupplierDao supplierDao = SupplierDaoMem.getInstance();

    private DaoDirector() {
    }

    public static DaoDirector getInstance() {
        return instance;
    }

    public List<Product> products() {
        return productDao.getAll();
    }

    public List<ProductCategory> productCategories() {
        return productCategoryDao.getAll();
    }

    public List<Supplier> suppliers() {
        return supplierDao.getAll();
    }

    public List<Product> productsBy(ProductFilteringOptions filteringOptions) {
        return filteringOptions.hasOptions() ? productDao.getBy(filteringOptions) : products();
    }
}
