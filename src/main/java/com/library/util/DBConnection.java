package com.library.util;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection conn;

    @SneakyThrows
    public static Connection getConnection() {
        if (conn == null) {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "postgres");
        }
        return conn;
    }
}
