package com.codecool.shop.data.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Executor {

    public <R> void execute(StatementProvider statementProvider, Extractor<R> extractor) {
        try (Connection connection = LiveConnection.getInstance().getConnection();
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
