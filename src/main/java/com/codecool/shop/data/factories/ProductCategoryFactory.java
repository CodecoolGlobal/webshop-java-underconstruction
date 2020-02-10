package com.codecool.shop.data.factories;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;

public class ProductCategoryFactory extends Factory {

    private static Factory self = new ProductCategoryFactory();
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

    private ProductCategoryFactory() {}

    public static Factory getInstance() {
        return self;
    }

    @Override
    protected void synthesize(String[] entries) {
        int id = Integer.parseInt(entries[0]);
        String name = entries[1];
        String department = entries[2];
        String description = entries[3];
        ProductCategory productCategory = new ProductCategory(name, department, description);
        productCategory.setId(id);
        productCategoryDataStore.add(productCategory);
    }
}
