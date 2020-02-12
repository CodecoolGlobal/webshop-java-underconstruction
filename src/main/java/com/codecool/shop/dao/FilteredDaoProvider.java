package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class FilteredDaoProvider {

    private static FilteredDaoProvider instance = new FilteredDaoProvider();

    private ProductDao productDao = ProductDaoMem.getInstance();
    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private SupplierDao supplierDao = SupplierDaoMem.getInstance();

    private FilteredDaoProvider() {
    }

    public static FilteredDaoProvider getInstance() {
        return instance;
    }

    public List<Product> products() {
        return productDao.getAll();
    }

    public List<Product> productsByProductCategory(int productCategoryId) {
        if (productCategoryId == -1) {
            return productDao.getAll();
        } else {
            ProductCategory selectedCategory = productCategoryDao.find(productCategoryId);
            return productDao.getBy(selectedCategory);
        }
    }

    public List<Product> productsBySupplier(int supplierId) {
        if (supplierId == -1) {
            return productDao.getAll();
        } else {
            Supplier supplier = supplierDao.find(supplierId);
            return productDao.getBy(supplier);
        }
    }

    public List<ProductCategory> productCategories() {
        return productCategoryDao.getAll();
    }

    public List<Supplier> suppliers() {
        return supplierDao.getAll();
    }
}
