package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.requestprocessing.ajax.CheckoutRequestJsonConverter;
import com.codecool.shop.dao.sqlImplementation.CustomerDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.OrderDaoJDBC;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OrderHistoryRequestProcessor extends AbstractRequestProcessor {

    private CheckoutRequestJsonConverter jsonConverter = new CheckoutRequestJsonConverter();
    private SessionHandler sessionHandler = new SessionHandler();

    @Override
    public void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException {
        strategy.invokeMethod(req, resp, this);

    }

    @Override
    void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = sessionHandler.getSession(req);

        context.setVariable("page_path", "order_history/order_history.html");

        //int userId = sessionHandler.getUserIdFromSession(session);
        // delete after user login implemented
        int userId = 1;

        List<HashMap<String, String>> orderHistory = getCustomerOrderHistory(userId);
        System.out.println(orderHistory);
        context.setVariable("orderHistory", orderHistory);

        engine.process("layout.html", context, resp.getWriter());
    }

    private List<HashMap<String, String>> getCustomerOrderHistory(int userId) {
        CustomerDaoJDBC customerDaoJDBC = new CustomerDaoJDBC();
        OrderDaoJDBC orderDaoJDBC = new OrderDaoJDBC();

        int customerId = customerDaoJDBC.getCustomerIdByUserId(userId);
        return orderDaoJDBC.searchOrderByCustomerId(customerId);
    }

}
