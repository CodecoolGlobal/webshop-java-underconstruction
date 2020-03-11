package com.codecool.shop.controller.requestprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum RequestProcessingStrategy {
    DEFAULT("defaultResponse"),
    FILTER_PRODUCTS("filterProducts"),
    UPDATE_ORDER("updateOrder"),
    HANDLE_CUSTOMER("handleCustomer");


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
