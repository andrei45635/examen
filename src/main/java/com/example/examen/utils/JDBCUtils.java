package com.example.examen.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private final String url = "jdbc:postgresql://localhost:5432/examen";
    private final String user = "postgres";
    private final String passwd = "starwars4563";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            System.out.println("Error when getting connection " + e);
        }
        return connection;
    }
}
