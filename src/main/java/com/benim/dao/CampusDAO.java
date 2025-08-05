package com.benim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.benim.model.Campus;
import com.benim.utils.DBUtil;

public class CampusDAO{

    private static final String INSERT_SQL = "INSERT INTO campus(university_id, name,city, district, address, created_by) values (?,?,?,?,?,?) ";
    private static final String UPDATE_SQL = "UPDATE campus SET name = ?, city = ?, district = ?, address = ?,updated_by = ?, updated_at = NOW()  WHERE id = ?";
    private static final String SOFT_DELETE_SQL = "UPDATE campus SET is_deleted = 1, deleted_by = ?, deleted_at = NOW() WHERE id = ?";
    private static final String SELECT_BY_UNI_SQL = "SELECT * FROM campus WHERE university_id = ? AND is_deleted = 0";

    public void insert(Campus c, String operator) throws SQLException{
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setInt(1, c.getUniversityId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getCity());
            ps.setString(4, c.getDistrict());
            ps.setString(5, c.getAddress());
            ps.setString(6, operator);
            ps.executeUpdate();
        }
        logHistory(c, "INSERT", operator);
    }

    public void update(Campus c, String operator) throws SQLException{
        Campus oldCampus = selectById(c.getId());
        logHistory(oldCampus, "UPDATE", operator);
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setString(1, c.getName());
            ps.setString(2, c.getCity());
            ps.setString(3, c.getDistrict());
            ps.setString(4, c.getAddress());
            ps.setString(5, operator);
            ps.setInt(6, c.getId());
            ps.executeUpdate();
        }
    }

    public void softDelete(int campusId, String operator) throws SQLException{
        Campus oldCampus = selectById(campusId);
        logHistory(oldCampus, "DELETE", operator);
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(SOFT_DELETE_SQL)){
            ps.setString(1, operator);
            ps.setInt(2, campusId);
            ps.executeUpdate();
        }
    }

    public List<Campus> getCampusesByUniversityId(int universityId) throws SQLException {
        List<Campus> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_UNI_SQL)) {

            stmt.setInt(1, universityId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Campus c = new Campus();
                c.setId(rs.getInt("id"));
                c.setUniversityId(rs.getInt("university_id"));
                c.setName(rs.getString("name"));
                c.setCity(rs.getString("city"));
                c.setDistrict(rs.getString("district"));
                c.setAddress(rs.getString("address"));
                list.add(c);
            }
        }
        return list;
    }

    public Campus selectById(int id){
        String sql = "SELECT * FROM campus WHERE id = ?";
        Campus campus = null;

        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                campus = new Campus();
                campus.setId(rs.getInt("id"));
                campus.setName(rs.getString("name"));
                campus.setCity(rs.getString("city"));
                campus.setDistrict(rs.getString("district"));
                campus.setAddress(rs.getString("address"));
                campus.setUniversityId(rs.getInt("university_id"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return campus;
    }

    private void logHistory(Campus campus, String actionType, String operator){
        String sql = "INSERT INTO campus_history(campus_id, university_id, name, city, district, address, action_type,action_by, action_at) VALUES (?,?,?,?,?,?,?,?,NOW())";
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, campus.getId());
            ps.setInt(2, campus.getUniversityId());
            ps.setString(3, campus.getName());
            ps.setString(4, campus.getCity());
            ps.setString(5, campus.getDistrict());
            ps.setString(6, campus.getAddress());
            ps.setString(7, actionType);
            ps.setString(8, operator);

            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
}