package com.codecool.shop.data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Executor {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "kovacsg";
    private static final String DB_PASSWORD = "kvcsgrg_cdcl";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public <R> void execute(StatementProvider statementProvider, Extractor<R> extractor) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = statementProvider.get(connection);) {

            boolean hasResultSet = preparedStatement.execute();

            if (hasResultSet && extractor != null) {
                extractor.setData(preparedStatement.getResultSet());
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute(StatementProvider statementProvider) {
        execute(statementProvider, null);
    }


}