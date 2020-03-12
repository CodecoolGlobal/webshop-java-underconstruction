package com.codecool.shop.data.sql;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class OrderExtractor extends Extractor<HashMap<String, String>> {

    @Override
    public void extractObject() throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        HashMap<String, String> order = new HashMap<String, String>();

        order.put("totalPrice", Integer.toString(this.resultSet.getInt("total_price")));
        order.put("status", this.resultSet.getString("status"));
        order.put("checkoutDate", new SimpleDateFormat("yyyy-dd-MM HH:mm:ss")
                .format(this.resultSet.getTimestamp("checkout_date")));

        if(this.resultSet.getDate("payment_date") != null) {
            order.put("paymentDate", new SimpleDateFormat("yyyy-dd-MM HH:mm:ss")
                    .format(this.resultSet.getTimestamp("payment_date")));
        } else {
            order.put("paymentDate", null);
        }
        this.data.add(order);
    }

}
