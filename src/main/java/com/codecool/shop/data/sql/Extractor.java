package com.codecool.shop.data.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Extractor <R> {

    protected ResultSet resultSet;
    protected List<R> data = new ArrayList<>();

    public void setResult(ResultSet resultSet) {
        this.resultSet = resultSet;
    };

    public abstract void extractObject() throws SQLException;

    public void extractResult() throws SQLException {
        while (resultSet.next())
            extractObject();
    };

    public R fetchOne() {
        return data.get(0);
    }

    public List<R> fetchAll() {
        return data;
    }
}
