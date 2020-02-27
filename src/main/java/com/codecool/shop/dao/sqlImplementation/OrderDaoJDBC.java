package com.codecool.shop.dao.sqlImplementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

public class OrderDaoJDBC implements OrderDao {
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
}
