package com.codecool.shop.data.sql;

import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;

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
