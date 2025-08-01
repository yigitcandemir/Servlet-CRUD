package com.benim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.benim.model.University;
import com.benim.utils.DBUtil;

public class UniversityDAO {


    private static final String SELECT_ALL = "SELECT id, name, website from universiteler";
    private static final String INSERT_SQL = "INSERT INTO universiteler(name, website) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE universiteler SET name = ?, website = ? WHERE id = ?";
    private static final String SEARCH_BY_NAME = "SELECT id, name, website FROM universiteler WHERE name LIKE ?";
    private static final String DELETE_SQL = "DELETE FROM universiteler WHERE id = ?";


    public List<University> selectAll(){
        List <University> list = new ArrayList<>();
        try(java.sql.Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(SELECT_ALL)){
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
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(SEARCH_BY_NAME)) {
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

    public University selectById(int id){
        University university = null;
        String sql = "SELECT * FROM universiteler WHERE id = ?";

        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                university = new University(rs.getInt("id"), rs.getString("name"),rs.getString("website"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return university;
    }

    public void insert(University uni) throws SQLException{
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setString(1, uni.getName());
            ps.setString(2, uni.getWebsite());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void update(University uni) throws SQLException {
        try ( Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setString(1, uni.getName());
            ps.setString(2, uni.getWebsite());
            ps.setInt(3, uni.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException{
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }
}
