package com.benim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.benim.utils.DBUtil;

public class AdminDAO{

    private static final String LOGIN_SQL = "SELECT * FROM admin WHERE username =? and password =?";

    public boolean validate(String username, String password){
        try(Connection conn = DBUtil.getConnection("admins?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"); PreparedStatement ps = conn.prepareStatement(LOGIN_SQL)){
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