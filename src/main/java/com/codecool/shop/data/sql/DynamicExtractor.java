package com.codecool.shop.data.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DynamicExtractor<T> {
    private List<T> data = new ArrayList<>();
    private ResultSetProcessor<T> resultSetProcessor;

    public DynamicExtractor(ResultSetProcessor<T> resultSetProcessor) {
        this.resultSetProcessor = resultSetProcessor;
    }

    public void extractAllFrom(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            T obj = resultSetProcessor.getObjectFrom(resultSet);
            data.add(obj);
        }
    }

    public T fetchOne() {
        if (data.size() == 0)
            return null;

        T result = data.get(0);
        this.data = new ArrayList<>();
        return result;
    }

    public List<T> fetchAll() {
        List<T> result = data;
        data = new ArrayList<>();
        return result;
    }
}
