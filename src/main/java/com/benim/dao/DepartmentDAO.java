package com.benim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.benim.model.Department;
import com.benim.utils.DBUtil;


public class DepartmentDAO{

    private static final String INSERT_SQL = "INSERT INTO department(faculty_id, name) values(?,?)";
    private static final String UPDATE_SQL = "UPDATE department SET name = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM department WHERE id = ?";
    
    public void insert(Department d) throws SQLException{
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setInt(1, d.getFacultyId());
            ps.setString(2, d.getName());
            ps.executeUpdate();
        }
    }

    public void update(Department d) throws SQLException{
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setString(1, d.getName());
            ps.setInt(2, d.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException{
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Department> getDepartmentsByFacultyId(int facultyId) throws SQLException{
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM department where faculty_id = ?";

        try (java.sql.Connection conn = DBUtil.getConnection("universities"); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, facultyId);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Department d = new Department();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setFacultyId(rs.getInt("faculty_Id"));
                list.add(d);
            }
        } 
        return list;
    }
}