package com.codecool.shop.dao.sqlImplementation;


import com.codecool.shop.data.sql.*;
import com.codecool.shop.model.Customer;

import java.sql.PreparedStatement;
import java.sql.Types;

public class CustomerDaoJDBC {
    private Executor executor = new Executor();
    private Extractor<Integer> extractor = new CustomerIdExtractor();

    public Integer getCustomerIdByUserId(int userId) {
        String query = "SELECT customer_id FROM customer WHERE user_id = ?";

        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            return preparedStatement;
        };
        executor.execute(statementProvider, extractor);
        return extractor.fetchOne();
    }

    public int insertCustomerIntoTable(Customer customer) {
        String query =
                "INSERT INTO customer (first_name, last_name, email, phone_number, user_id) " +
                        "VALUES (?, ?, ?, ?, ?) RETURNING id";

        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhoneNumber());
            if (customer.getUserId() == null) {
                preparedStatement.setNull(5, Types.NULL);
            } else {
                preparedStatement.setInt(5, customer.getUserId());
            }

            return preparedStatement;
        };
        executor.execute(statementProvider, extractor);
        return extractor.fetchOne();
    }

}
