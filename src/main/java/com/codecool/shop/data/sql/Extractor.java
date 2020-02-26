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

    public void extractResult() {
        while (true) {
            try {
                if (!resultSet.next()) break;
                extractObject();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    public R fetchOne() {
        this.extractResult();
        return data.get(0);
    }

    public List<R> fetchAll() {
        this.extractResult();
        return data;
    }
}
