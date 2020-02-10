package com.codecool.shop.config;

import com.codecool.shop.data.DaoBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DaoBuilder.extractData(sce.getServletContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
