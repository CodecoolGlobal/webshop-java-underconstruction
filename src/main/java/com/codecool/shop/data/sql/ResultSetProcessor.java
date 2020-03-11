package com.codecool.shop.data.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetProcessor<T> {
    T getObjectFrom(ResultSet resultSet) throws SQLException;
}
