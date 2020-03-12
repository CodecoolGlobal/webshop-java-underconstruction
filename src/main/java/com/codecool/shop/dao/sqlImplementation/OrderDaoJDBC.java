package com.codecool.shop.dao.sqlImplementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.data.sql.*;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.util.Util;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

public class OrderDaoJDBC implements OrderDao {

    private Executor executor = new Executor();
    private Extractor<HashMap<String, String>> extractor = new OrderExtractor();

    @Override
    public void handleItemChange(Order order, int productId, int quantity) {
        Product product = new ProductDaoJDBC().getBy(productId);
        LineItem item;

        if (order.getLineItemBy(productId) == null) {
            item = new LineItem(product);
            order.add(item);
        }
        else if (quantity >= -1) {
            item = order.getLineItemBy(productId);

            if (quantity > 0) item.setQuantity(quantity);
            else if(quantity == 0) order.removeLineItemBy(item);
            else item.setQuantity(item.getQuantity() + 1);
        }
        order.setPriceTotal();
        order.setItemsTotal();
    }

    public void saveOrder(Order order) {
        String query =
                "INSERT INTO \"order\" (id, customer_id, total_price, status, checkout_date, payment_date, shipping_id, billing_id) " +
                        "VALUES (default , ?, ?, ?, ?, ? ,?, ?)";

        StatementProvider statementProvider = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getCustomer().getCustomerId());
            preparedStatement.setFloat(2, order.getPriceTotal());
            preparedStatement.setString(3, "checked");
            preparedStatement.setTimestamp(4, Util.getCurrentDate());
            preparedStatement.setNull(5, Types.NULL);
            preparedStatement.setInt(6, order.getCustomer().getCustomerCurrentShippingAddressId());
            preparedStatement.setInt(7, order.getCustomer().getCustomerCurrentBillingAddressId());

            return preparedStatement;
        };
        executor.execute(statementProvider);
    }

    public List<HashMap<String, String>> searchOrderByCustomerId(int customerId) {
        String query = "SELECT total_price, status, checkout_date, payment_date FROM \"order\" WHERE customer_id = ?";

        StatementProvider statementProvider = connection -> {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            return preparedStatement;
        };
        executor.execute(statementProvider, extractor);

        return extractor.fetchAll();
    }
}
