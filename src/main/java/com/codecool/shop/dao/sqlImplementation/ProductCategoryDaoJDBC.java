package com.codecool.shop.dao.sqlImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.Extractor;
import com.codecool.shop.data.sql.ProductCategoryExtractor;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private Executor executor = new Executor();
    private Extractor<ProductCategory> extractor = new ProductCategoryExtractor();


    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
