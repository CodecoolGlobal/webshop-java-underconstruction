package com.codecool.shop.dao.sqlImplementation;

import com.codecool.shop.controller.requestprocessing.filtering.ProductFilteringStrategy;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.Extractor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.util.List;

public class ProductDaoJDBC implements ProductDao  {

    private Executor executor = new Executor();
    private Extractor<Product> extractor = new ProductExtractor();

    @Override
    public void add(Product product) {
        String query =
                "INSERT INTO product (id, name, description, default_price, currency, supplier_id, category_id)" +
                "VALUES (default, ?, ?, ?, ?, ?, ?)";
        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getDefaultPrice());
            preparedStatement.setString(4, product.getDefaultCurrency().toString());
            preparedStatement.setInt(5, product.getSupplier().getId());
            preparedStatement.setInt(6, product.getProductCategory().getId());

            return preparedStatement;
        };

        executor.execute(statementProvider);
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier, ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductFilteringStrategy productFilteringStrategy) {
        return null;
    }

    @Override
    public Product getBy(int productId) {
        return null;
    }
}
