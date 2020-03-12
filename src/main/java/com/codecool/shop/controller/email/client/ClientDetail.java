package com.codecool.shop.controller.email.client;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

public class ClientDetail {

    public ClientDetail() {
    }

    public String getRecipient(Order order) {
        return order.getCustomer().getEmail();
    }


    public String prepareEmailContent(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h3>Dear ");
        sb.append(order.getCustomer().getFirstName());
        sb.append(" ");
        sb.append(order.getCustomer().getLastName());
        sb.append(",</h3>");
        sb.append("<p>");
        sb.append("<h3> you have ordered the following items:</h3>");
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<table>");
        sb.append("<tbody>");

        sb.append("<tr>");
        sb.append("<td>");
        sb.append("</td>");
        for (LineItem lineItem: order.getCart()) {
            sb.append("<tr>");
            sb.append(lineItem.getProduct().getName());
            sb.append(": ");
            sb.append(lineItem.getProduct().getDefaultPrice() * lineItem.getQuantity());
            sb.append("$");
            sb.append(" ");
            sb.append("(quantity: ");
            sb.append(lineItem.getQuantity());
            sb.append(")");
            sb.append("</tr>");
        }
        sb.append("</tr>");
        sb.append("</tbody>");
        sb.append("</table>");
        sb.append("</p>");
        sb.append("Total: ");
        sb.append(order.getPriceTotal());
        sb.append("$");
        return sb.toString();
    }

}
