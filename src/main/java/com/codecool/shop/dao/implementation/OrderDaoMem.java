package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

public class OrderDaoMem implements OrderDao {

    @Override
    public void handleAddItem(Order order, int productId) {
        Product product = ProductDaoMem.getInstance().getBy(productId);
        LineItem item;

        if (order.getLineItemBy(productId) == null) {
            item = new LineItem(product);
            order.add(item);
        }
         else {
             item = order.getLineItemBy(productId);
             item.setQuantity(item.getQuantity() + 1);

        }
    }

    @Override
    public void handleRemoveItem(Order order, int productId) {
        LineItem item = order.getLineItemBy(productId);
        int newQuantity = item.getQuantity() - 1;

        if (newQuantity == 0)
            order.removeLineItemBy(item);

        else
            item.setQuantity(newQuantity);
    }
}
