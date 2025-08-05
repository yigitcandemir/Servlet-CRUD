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


    private static final String SELECT_ALL = "SELECT id, name, website FROM universiteler WHERE is_deleted = 0";
    private static final String INSERT_SQL = "INSERT INTO universiteler(name, website, created_by) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE universiteler SET name = ?, website = ? updated_by = ?, updated_at = NOW() WHERE id = ? AND is_deleted = 0";
    private static final String SEARCH_BY_NAME = "SELECT id, name, website FROM universiteler WHERE name LIKE ?";
    private static final String DELETE_SQL = "UPDATE universiteler SET is_deleted = 1, deleted_by = ?, deleted_at = NOW() WHERE id = ?";


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
        String sql = "SELECT * FROM universiteler WHERE id = ? AND is_deleted = 0";

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

    public void insert(University uni, String operator) throws SQLException{
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setString(1, uni.getName());
            ps.setString(2, uni.getWebsite());
            ps.setString(3, operator);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        logHistory(uni, "INSERT", operator);
    }

    public void update(University uni, String operator) throws SQLException {
        University old = selectById(uni.getId());
        logHistory(old, "UPDATE", operator);
        try ( Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setString(1, uni.getName());
            ps.setString(2, uni.getWebsite());
            ps.setString(3, operator);
            ps.setInt(4, uni.getId());
            ps.executeUpdate();
        }
    }

    public void softDelete(int id, String operator) throws SQLException{
        University old = selectById(id);
        logHistory(old, "DELETE", operator);
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)){
            ps.setString(1,operator);
            ps.setInt(2,id);
            ps.executeUpdate();
        }
    }

    public void logHistory(University u, String actionType, String operator){
        String sql = "INSERT INTO universiteler_history (university_id, name, website, action_type, action_by, action_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, u.getId());
            ps.setString(2, u.getName());
            ps.setString(3, u.getWebsite());
            ps.setString(4, actionType);
            ps.setString(5, operator);
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
