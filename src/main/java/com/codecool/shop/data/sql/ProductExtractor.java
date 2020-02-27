package com.codecool.shop.data.sql;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.sqlImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.SupplierDaoJDBC;
import com.codecool.shop.model.Product;


import java.sql.SQLException;

public class ProductExtractor extends Extractor<Product> {

    @Override
    public void extractObject() throws SQLException {
        ProductCategoryDao categoryDao = new ProductCategoryDaoJDBC();
        SupplierDao supplierDao = new SupplierDaoJDBC();

        this.data.add(
                new Product(
                    this.resultSet.getInt("id"),
                    this.resultSet.getString("name"),
                    this.resultSet.getFloat("default_price"),
                    this.resultSet.getString("currency"),
                    this.resultSet.getString("description"),
                    categoryDao.find(this.resultSet.getInt("category_id")),
                    supplierDao.find(this.resultSet.getInt("supplier_id"))
                )
        );
    }
}
