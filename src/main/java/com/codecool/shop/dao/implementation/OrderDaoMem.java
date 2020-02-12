package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;


public class OrderDaoMem implements OrderDao {

    @Override
    public void handleItemChange(Order order, int productId, int quantity) {
        Product product = ProductDaoMem.getInstance().getBy(productId);
        LineItem item;

        if (order.getLineItemBy(productId) == null) {
            item = new LineItem(product);
            order.add(item);
        }
        else if (quantity > 0) {
            item = order.getLineItemBy(productId);
            item.setQuantity(quantity);
        }
        order.setPriceTotal();
        order.setItemsTotal();
    }
}
