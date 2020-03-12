package com.codecool.shop.dao.sqlImplementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.data.sql.*;
import com.codecool.shop.model.User;

import java.sql.PreparedStatement;

public class UserDaoJDBC implements UserDao {

    private static final Executor executor = new Executor();
    private static final Extractor<User> extractor = new UserExtractor();

    @Override
    public User add(User user) throws SecurityException {
        String query = "INSERT INTO \"user\" (id, username, password) VALUES(DEFAULT, ?, ?) RETURNING id, username, password";
        StatementProvider statementProvider = connection -> {
            if (!user.isSecure()) {
                throw new SecurityException("User password has not been hashed!");
            }
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            return statement;
        };
        executor.execute(statementProvider, extractor);
        return extractor.fetchOne();
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public User getBy(String username) {
        String query = "SELECT id, username, password FROM \"user\" WHERE username = ?";
        StatementProvider statementProvider = connection -> {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            return statement;
        };
        executor.execute(statementProvider, extractor);
        return extractor.fetchOne();
    }

    @Override
    public boolean isUnique(String username) {
        String query = "SELECT username FROM \"user\" WHERE username = ?";
        StatementProvider statementProvider = connection -> {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            return statement;
        };
        ResultSetProcessor<String> processor = resultSet -> resultSet.getString("username");
        DynamicExtractor<String> extractor = new DynamicExtractor<>(processor);
        executor.executeWithDynamicExtractor(statementProvider, extractor);
        return extractor.fetchOne() == null;
    }
}
