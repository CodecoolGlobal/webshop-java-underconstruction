package com.codecool.shop.data.sql;

import java.sql.SQLException;

public class CustomerIdExtractor extends Extractor<Integer> {

    @Override
    public void extractObject() throws SQLException {
        this.data.add(this.resultSet.getInt("id"));
    }

}
