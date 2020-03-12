package com.codecool.shop.controller.requestprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum RequestProcessingStrategy {
    DEFAULT("defaultResponse"),
    FILTER_PRODUCTS("filterProducts"),
    UPDATE_ORDER("updateOrder"),
    ADD_CUSTOMER_TO_ORDER("addCustomerToOrder"),
    REGISTER_USER("registerUser"),
    LOGIN_USER("loginUser"),
    SEND_EMAIL_TO_CLIENT("sendEmailToClient"),
    HANDLE_CUSTOMER("handleCustomer"),
    GET_ORDER_HISTORY("getOrderHistory");


    private final String methodName;

    RequestProcessingStrategy(String methodName) {
        this.methodName = methodName;
    }

    public void invokeMethod(HttpServletRequest req, HttpServletResponse resp, IRequestProcessor rp) {
        try {
            Method method = rp.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(rp, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
