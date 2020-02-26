package com.codecool.shop.data.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperties {

    private static Properties connectionProperties;

    public static void readConnectionProperties(String url) {
        try (InputStream input = new FileInputStream(url)) {

            connectionProperties = new Properties();

            // load a properties file
            connectionProperties.load(input);

            // get the property value and print it out


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(connectionProperties.getProperty("url"));
        System.out.println(connectionProperties.getProperty("database"));
        System.out.println(connectionProperties.getProperty("user"));
    }


}
