package com.codecool.shop.dao;

import com.codecool.shop.dao.sqlImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.ProductDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.SupplierDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class DaoDirector {

    private static DaoDirector instance = new DaoDirector();

    private ProductDao productDao = new ProductDaoJDBC();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
    private SupplierDao supplierDao = new SupplierDaoJDBC();

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

    public ProductDao getProductDao() {
        return productDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }
}
