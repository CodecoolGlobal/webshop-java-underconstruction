package com.codecool.shop.data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiveConnection {

    private static LiveConnection instance = null;

    private static final String DATABASE = ConnectionProperties.getDatabase();
    private static final String DB_USER = ConnectionProperties.getDbUser();
    private static final String DB_PASSWORD = ConnectionProperties.getPassword();

    private Connection connection;

    public LiveConnection() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
    }

    public static LiveConnection getInstance() throws SQLException {
        if (instance == null)
            LiveConnection.instance = new LiveConnection();

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}