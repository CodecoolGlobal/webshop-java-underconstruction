package com.codecool.shop.data.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Executor {

    public <R> void execute(StatementProvider statementProvider, Extractor<R> extractor) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = statementProvider.get(connection);) {

            boolean hasResultSet = preparedStatement.execute();

            if (hasResultSet && extractor != null) {
                extractor.setResult(preparedStatement.getResultSet());
                extractor.extractResult();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute(StatementProvider statementProvider) {
        execute(statementProvider, null);
    }

    public <T> void executeWithDynamicExtractor(StatementProvider statementProvider, DynamicExtractor<T> dynamicExtractor) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = statementProvider.get(connection)) {

            boolean hasResultSet = preparedStatement.execute();

            if (hasResultSet && dynamicExtractor != null) {
                dynamicExtractor.extractAllFrom(preparedStatement.getResultSet());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
