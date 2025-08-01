package com.benim.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil{
    private static String jdbcURL = "jdbc:mysql://localhost:3306/";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "12345";

    public static Connection getConnection(String dbName) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL + dbName, jdbcUsername, jdbcPassword);
        } 
        catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }


}