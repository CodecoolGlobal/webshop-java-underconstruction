package com.codecool.shop.dao.sqlImplementation;

import com.codecool.shop.data.sql.CustomerIdExtractor;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.Extractor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.model.Customer;

import java.sql.PreparedStatement;

public class CustomerAddressDaoJDBC {

    private Executor executor = new Executor();
    private Extractor<Integer> extractor = new CustomerIdExtractor();

    public void insertCustomerAddressIntoTable(Customer customer, String addressType) {
        String query =
                "INSERT INTO customer_address (customer_id, zip_code, city, address, address_type) " +
                        "VALUES (?, ?, ?, ?, ?)";

        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customer.getCustomerId());

            preparedStatement.setString(2, addressType.equals("shipping")
                    ? customer.getShippingDetails().getZipCode() : customer.getBillingDetails().getZipCode());

            preparedStatement.setString(3, addressType.equals("shipping")
                    ? customer.getShippingDetails().getCity() : customer.getBillingDetails().getCity());

            preparedStatement.setString(4, addressType.equals("shipping")
                    ? customer.getShippingDetails().getAddress() : customer.getBillingDetails().getAddress());

            preparedStatement.setString(5, addressType);

            return preparedStatement;
        };
        executor.execute(statementProvider);
    }

    public Integer searchForAddressGivenByCustomer(Customer customer, String addressType) {
        String query = "SELECT customer_id FROM customer WHERE customer_id = ? AND zip_code = ?" +
                "AND city = ? AND address = ? AND address_type = ? VALUES(?, ?, ?, ?, ?)";

        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customer.getCustomerId());

            preparedStatement.setString(2, addressType.equals("shipping")
                    ? customer.getShippingDetails().getZipCode() : customer.getBillingDetails().getZipCode());

            preparedStatement.setString(3, addressType.equals("shipping")
                    ? customer.getShippingDetails().getCity() : customer.getBillingDetails().getCity());

            preparedStatement.setString(4, addressType.equals("shipping")
                    ? customer.getShippingDetails().getAddress() : customer.getBillingDetails().getAddress());

            preparedStatement.setString(5, addressType);


            return preparedStatement;
        };
        executor.execute(statementProvider, extractor);
        return extractor.fetchOne();
    }

}
