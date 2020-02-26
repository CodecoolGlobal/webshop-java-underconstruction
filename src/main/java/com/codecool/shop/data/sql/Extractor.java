package com.codecool.shop.data.sql;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class Extractor <R> {

    private List<R> data = new ArrayList<>();

    public abstract void setData(ResultSet resultSet);
}
