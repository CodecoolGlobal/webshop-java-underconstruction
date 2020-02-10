package com.codecool.shop.data.factories;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

public class ProductFactory extends Factory {

    private static Factory self = new ProductFactory();
    private static ProductDao productDataStore = ProductDaoMem.getInstance();
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

    private ProductFactory() {}

    public static Factory getInstance() {
        return self;
    }

    @Override
    protected void synthesize(String[] entries) {
        int id = Integer.parseInt(entries[0]);
        String name = entries[1];
        float defaultPrice = Float.parseFloat(entries[2]);
        String currencyString = entries[3];
        String description = entries[4];

        int productCategoryId = Integer.parseInt(entries[5]);
        int supplierId = Integer.parseInt(entries[6]);
        ProductCategory productCategory = productCategoryDataStore.find(productCategoryId);
        Supplier supplier = supplierDataStore.find(supplierId);

        Product product = new Product(name, defaultPrice, currencyString, description, productCategory, supplier);
        product.setId(id);
        productDataStore.add(product);
    }
}
