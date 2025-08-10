package com.pahanaedu.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/pahanaedu?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";       // change if your DB user is different
    private static final String PASSWORD = "";       // change to your DB password

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("MySQL JDBC Driver not found.", e);
            }
        }
        return connection;
    }
}

