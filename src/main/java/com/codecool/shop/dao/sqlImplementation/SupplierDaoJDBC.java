package com.codecool.shop.dao.sqlImplementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.Extractor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.data.sql.SupplierExtractor;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {
    private Executor executor = new Executor();
    private Extractor<Supplier> extractor = new SupplierExtractor();


    @Override
    public void add(Supplier supplier) {
        String query =
                "INSERT INTO supplier (id, name, description)" +
                "VALUES (default, ?, ?)";
        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getDescription());

            return preparedStatement;
        };
        executor.execute(statementProvider);
    }

    @Override
    public Supplier find(int id) {
        String query =
                "SELECT * FROM supplier WHERE id = ?";

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
        String query = "DELETE FROM supplier WHERE id = ?";
        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            return preparedStatement;
        };
        executor.execute(statementProvider);
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier";
        StatementProvider statementProvider = connection -> connection.prepareStatement(query);

        executor.execute(statementProvider);

        return extractor.fetchAll();
    }
}
