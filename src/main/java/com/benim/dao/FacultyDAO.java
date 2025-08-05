package com.benim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.benim.model.Faculty;
import com.benim.utils.DBUtil;

public class FacultyDAO {
    private static final String INSERT_SQL = "INSERT INTO faculty(name,campus_id,telephone, dean, created_by) values(?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE faculty SET name = ?, telephone = ?, dean = ?, updated_by = ?, updated_at = NOW() WHERE id = ?";
    private static final String SOFT_DELETE_SQL = "UPDATE faculty SET is_deleted = 1, deleted_by = ?, deleted_at = NOW() WHERE id = ?";
    private static final String SELECT_BY_CAMPUS = "SELECT * FROM faculty WHERE campus_id = ? AND is_deleted = 0";
    
    public void insert(Faculty f, String operator) throws SQLException{
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setString(1, f.getName());
            ps.setInt(2, f.getCampusId());
            ps.setString(3, f.getTelephone());
            ps.setString(4, f.getDean());
            ps.setString(5, operator);
            ps.executeUpdate();
        }
        logHistory(f, "INSERT", operator); 
    }

    public void update(Faculty f, String operator) throws SQLException{
        Faculty old = selectById(f.getId());
        logHistory(old, "UPDATE", operator);
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setString(1, f.getName());
            ps.setString(2, f.getTelephone());
            ps.setString(3, f.getDean());
            ps.setString(4, operator);
            ps.setInt(5, f.getId());
            ps.executeUpdate();
        }
    }

    public void softDelete(int id, String operator) throws SQLException {
        Faculty old = selectById(id);
        logHistory(old, "DELETE", operator);
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(SOFT_DELETE_SQL)){
            ps.setString(1, operator);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public List<Faculty> getFacultiesByCampusId(int campusId) throws SQLException{
        List<Faculty> list = new ArrayList<>();

        try (java.sql.Connection conn = DBUtil.getConnection("universities"); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_CAMPUS)){
            stmt.setInt(1, campusId);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Faculty f = new Faculty();
                f.setId(rs.getInt("id"));
                f.setName(rs.getString("name"));
                f.setTelephone(rs.getString("telephone"));
                f.setDean(rs.getString("dean"));
                f.setCampusId(rs.getInt("campus_id"));
                list.add(f);
            }
        }
        return list;
    }

    public List<Faculty> getFacultiesByUniversityId(int universityId) throws SQLException{
        List<Faculty> facultyList = new ArrayList<>();

        String sql = "SELECT id FROM campus WHERE university_id = ? AND is_deleted = 0";

        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement campusStmt = conn.prepareStatement(sql)){
            campusStmt.setInt(1, universityId);
            ResultSet campusRs = campusStmt.executeQuery();

            while(campusRs.next()){
                int campusId = campusRs.getInt("id");
                facultyList.addAll(getFacultiesByCampusId(campusId));
            }
        }

        return facultyList;
    }

    public Faculty selectById(int id){
        String sql = "SELECT * FROM faculty WHERE id = ?";
        Faculty faculty = null;

        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                faculty = new Faculty();
                faculty.setId(rs.getInt("id"));
                faculty.setName(rs.getString("name"));
                faculty.setTelephone(rs.getString("telephone"));
                faculty.setDean(rs.getString("dean"));
                faculty.setCampusId(rs.getInt("campus_id"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return faculty;
    }

    public void logHistory(Faculty faculty, String actionType, String operator){
        String sql = "INSERT INTO faculty_history(faculty_id,name,telephone,dean,campus_id,action_type,action_by,action_at) VALUES (?,?,?,?,?,?,?,NOW())";

        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, faculty.getId());
            ps.setString(2, faculty.getName());
            ps.setString(3, faculty.getTelephone());
            ps.setString( 4, faculty.getDean());
            ps.setInt(5, faculty.getCampusId());
            ps.setString(6, actionType);
            ps.setString(7, operator);
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
