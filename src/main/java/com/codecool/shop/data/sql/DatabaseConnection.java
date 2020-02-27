package com.codecool.shop.data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance = null;

    private static final String DATABASE = ConnectionProperties.getDatabase();
    private static final String DB_USER = ConnectionProperties.getDbUser();
    private static final String DB_PASSWORD = ConnectionProperties.getPassword();

    private Connection connection;

    public DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null)
            DatabaseConnection.instance = new DatabaseConnection();

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
