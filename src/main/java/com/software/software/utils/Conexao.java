package com.software.software.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    public static Connection getConnectionPostgres() throws ClassNotFoundException, SQLException {
        driver = "org.postgresql.Driver";
        url = "jdbc:postgresql://192.168.99.100:5432/es3";
        user = "postgres";
        password = "123456";
        return getConnection();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
