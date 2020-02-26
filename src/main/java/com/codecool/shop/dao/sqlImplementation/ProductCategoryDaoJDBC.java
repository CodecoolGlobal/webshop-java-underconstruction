package com.codecool.shop.dao.sqlImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.Extractor;
import com.codecool.shop.data.sql.ProductCategoryExtractor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.model.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private Executor executor = new Executor();
    private Extractor<ProductCategory> extractor = new ProductCategoryExtractor();

    @Override
    public void add(ProductCategory category) {
        String query =
                "INSERT INTO product_category (id, name, department, description) " +
                "VALUES (default, ?, ?, ?)";

        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDepartment());
            preparedStatement.setString(3, category.getDescription());

            return preparedStatement;
        };
        executor.execute(statementProvider);
    }

    @Override
    public ProductCategory find(int id) {
        String query =
                "SELECT * FROM product_category WHERE id = ?";

        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            return preparedStatement;
        };

        executor.execute(statementProvider, extractor);

        return extractor.fetchOne();
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product_category WHERE id = ?";

        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            return preparedStatement;
        };
        executor.execute(statementProvider);
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM product_category";

        StatementProvider statementProvider = connection -> connection.prepareStatement(query);
        executor.execute(statementProvider, extractor);

        return extractor.fetchAll();
    }
}
