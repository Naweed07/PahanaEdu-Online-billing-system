package com.pahanaedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/pahanaedu?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";    // change as per your MySQL user
    private static final String PASSWORD = "";    // change as per your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
