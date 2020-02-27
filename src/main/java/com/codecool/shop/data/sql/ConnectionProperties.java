package com.codecool.shop.data.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperties {

    private static Properties connectionProperties;

    public static void readFrom(String configFilePath) {
        try (InputStream input = new FileInputStream(configFilePath)) {

            connectionProperties = new Properties();
            connectionProperties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
