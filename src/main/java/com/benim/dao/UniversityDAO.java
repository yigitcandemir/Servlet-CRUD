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

    private static final String SELECT_ALL = "SELECT id, name, website from universiteler";
    private static final String INSERT_SQL = "INSERT INTO universiteler(name, website) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE universiteler SET name = ?, website = ? WHERE id = ?";
    private static final String SEARCH_BY_NAME = "SELECT id, name, website FROM universiteler WHERE name LIKE ?";
    private static final String DELETE_SQL = "DELETE FROM universiteler WHERE id = ?";

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
                list.add(new University(rs.getInt("id"),rs.getString("name"), rs.getString("website")));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<University> searchByName(String name) {
        List<University> list = new ArrayList<>();
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(SEARCH_BY_NAME)) {
            ps.setString(1, name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new University(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("website")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(University uni) throws SQLException{
        try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setString(1, uni.getName());
            ps.setString(2, uni.getWebsite());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void update(University uni) throws SQLException {
        try ( Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setString(1, uni.getName());
            ps.setString(2, uni.getWebsite());
            ps.setInt(3, uni.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException{
        try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }
}
