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
    private static final String INSERT_SQL = "INSERT INTO faculty(name,campus_id,telephone, dean) values(?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE faculty SET name = ?, telephone = ?, dean = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM faculty WHERE id = ?";
    
    public void insert(Faculty f) throws SQLException{
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setString(1, f.getName());
            ps.setInt(2, f.getCampusId());
            ps.setString(3, f.getTelephone());
            ps.setString(4, f.getDean());
            ps.executeUpdate();
        } 
    }

    public void update(Faculty f) throws SQLException{
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setString(1, f.getName());
            ps.setString(2, f.getTelephone());
            ps.setString(3, f.getDean());
            ps.setInt(4, f.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Faculty> getFacultiesByCampusId(int campusId) throws SQLException{
        List<Faculty> list = new ArrayList<>();
        String sql = "SELECT * FROM faculty WHERE campus_id = ?";

        try (java.sql.Connection conn = DBUtil.getConnection("universities"); PreparedStatement stmt = conn.prepareStatement(sql)){
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

        String CampusSql = "SELECT id FROM campus WHERE university_id = ?";

        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement campusStmt = conn.prepareStatement(CampusSql)){
            campusStmt.setInt(1, universityId);
            ResultSet campusRs = campusStmt.executeQuery();

            while(campusRs.next()){
                int campusId = campusRs.getInt("id");

                List<Faculty> faculties = getFacultiesByCampusId(campusId);
                facultyList.addAll(faculties);
            }
        }

        return facultyList;
    }
}
