package com.codecool.shop.data.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementProvider {

    PreparedStatement get(Connection connection) throws SQLException;
}
