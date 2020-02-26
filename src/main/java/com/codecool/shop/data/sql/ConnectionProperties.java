package com.codecool.shop.data.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperties {

    private static Properties connectionProperties;

    public static void readFrom(String url) {
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


    public static String getDatabase() {
        return "jdbc:postgresql://"
                + connectionProperties.getProperty("url") + "/"
                + connectionProperties.getProperty("database");
    }

    public static String getDbUser() {
        return connectionProperties.getProperty("user");
    }

    public static String getPassword() {
        return connectionProperties.getProperty("password");
    }
}
