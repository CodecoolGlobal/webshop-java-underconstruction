package com.codecool.shop.data.sql;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.sqlImplementation.ProductDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierExtractor extends Extractor<Supplier> {

    @Override
    public void extractObject() throws SQLException {
        Supplier supplier = new Supplier(
                this.resultSet.getInt("id"),
                this.resultSet.getString("name"),
                this.resultSet.getString("description")
        );
        ProductDao productDao = new ProductDaoJDBC();
        // List<Product> supplierProducts = productDao.getBy(supplier);
        // supplier.setProducts((ArrayList<Product>) supplierProducts);

        this.data.add(supplier);
    }
}
