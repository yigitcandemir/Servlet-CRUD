package com.benim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/admins?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345";

    private static final String LOGIN_SQL = "SELECT * FROM admin WHERE username =? and password =?";

    protected Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean validate(String username, String password){
        try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(LOGIN_SQL)){
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}