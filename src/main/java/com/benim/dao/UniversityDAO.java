package com.benim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.benim.model.University;

public class UniversityDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/universities?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345";

    private static final String SELECT_ALL = "SELECT name, website from universiteler";
    private static final String INSERT_SQL = "INSERT INTO universiteler(name, website) VALUES (?, ?)";

    protected Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } 
        catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

    public List<University> selectAll(){
        List <University> list = new ArrayList<>();
        try(java.sql.Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new University(rs.getString("name"), rs.getString("website")));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void insert(University uni){
        try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setString(1, uni.getName());
            ps.setString(2, uni.getWebsite());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
