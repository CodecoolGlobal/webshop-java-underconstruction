package com.codecool.shop.data.sql;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.sqlImplementation.ProductDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryExtractor extends Extractor<ProductCategory> {
    @Override
    public void extractObject() throws SQLException {
        ProductCategory category = new ProductCategory(
                this.resultSet.getInt("id"),
                this.resultSet.getString("name"),
                this.resultSet.getString("department"),
                null
        );
        // ProductDao productDao = new ProductDaoJDBC();
        // List<Product> categoryProducts = productDao.getBy(category);
        // category.setProducts((ArrayList<Product>) categoryProducts);

        this.data.add(category);
    }
}
