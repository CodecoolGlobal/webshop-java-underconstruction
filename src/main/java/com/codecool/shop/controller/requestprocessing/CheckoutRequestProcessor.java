package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.requestprocessing.ajax.CheckoutRequestJsonConverter;
import com.codecool.shop.dao.sqlImplementation.CustomerAddressDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.CustomerDaoJDBC;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class CheckoutRequestProcessor extends AbstractRequestProcessor {

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
        context.setVariable("page_path", "checkout/checkout.html");
        engine.process("layout.html", context, resp.getWriter());
    }

    void handleCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Customer> optionalCustomer = jsonConverter.parseCustomer(req);
        optionalCustomer.ifPresent(customer ->  {
            Order order = sessionHandler.getOrderFromSession(req);
            order.setCustomer(optionalCustomer.get());
            handleCustomerInDatabase(req, optionalCustomer.get());
        });
        String json = jsonConverter.parsingSuccessJson(optionalCustomer.isPresent());
        sendJson(resp, json);
    }

    private void handleCustomerInDatabase(HttpServletRequest req, Customer customer) {
        CustomerDaoJDBC customerDaoJDBC = new CustomerDaoJDBC();
        CustomerAddressDaoJDBC customerAddressDaoJDBC = new CustomerAddressDaoJDBC();

        HttpSession session = sessionHandler.getSession(req);
        Integer userId = sessionHandler.getUserIdFromSession(session);

        if(userId != null && customerDaoJDBC.getCustomerIdByUserId(userId) == null) {
                int customerId = customerDaoJDBC.insertCustomerIntoTable(customer);
                customer.setCustomerId(customerId);
            }

        handleAddress(customerAddressDaoJDBC, customer);
    }

    private void handleAddress(CustomerAddressDaoJDBC customerAddressDaoJDBC, Customer customer) {

        Integer shippingCustomerId = customerAddressDaoJDBC.searchForAddressGivenByCustomer(customer, "shipping");
        Integer billingCustomerId = customerAddressDaoJDBC.searchForAddressGivenByCustomer(customer, "billing");

        if(shippingCustomerId == null) {
            customerAddressDaoJDBC.insertCustomerAddressIntoTable(customer, "shipping");
        }

        if(billingCustomerId == null) {
            customerAddressDaoJDBC.insertCustomerAddressIntoTable(customer, "billing");
        }
    }

}
