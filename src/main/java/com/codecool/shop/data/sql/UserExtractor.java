package com.codecool.shop.data.sql;

import com.codecool.shop.model.User;

import java.sql.SQLException;

public class UserExtractor extends Extractor<User> {

    @Override
    public void extractObject() throws SQLException {
        int id = this.resultSet.getInt("id");
        String username = this.resultSet.getString("username");
        User user = new User(id, username);
        this.data.add(user);
    }
}
