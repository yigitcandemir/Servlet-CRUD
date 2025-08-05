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

    private static final String INSERT_SQL = "INSERT INTO department(faculty_id, name, created_by) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE department SET name = ?, updated_by = ?, updated_at = NOW() WHERE id = ?";
    private static final String SOFT_DELETE_SQL = "UPDATE department SET is_deleted = 1, deleted_by = ?, deleted_at = NOW() WHERE id = ?";
    private static final String SELECT_BY_FACULTY = "SELECT * FROM department WHERE faculty_id = ? AND is_deleted = 0";
    
    public void insert(Department d, String operator) throws SQLException{
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setInt(1, d.getFacultyId());
            ps.setString(2, d.getName());
            ps.setString(3, operator);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                d.setId(rs.getInt(1));
            }
        }
        logHistory(d, "INSERT", operator);
    }

    public void update(Department d, String operator) throws SQLException{
        Department old = selectById(d.getId());
        logHistory(old, "UPDATE", operator);

        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setString(1, d.getName());
            ps.setString(2, operator);
            ps.setInt(3, d.getId());
            ps.executeUpdate();
        }
    }

    public void softDelete(int id, String operator) throws SQLException{
        Department old = selectById(id);
        logHistory(old, "DELETE", operator);

        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(SOFT_DELETE_SQL)){
            ps.setString(1, operator);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public List<Department> getDepartmentsByFacultyId(int facultyId) throws SQLException{
        List<Department> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_FACULTY)) {
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

    public void logHistory(Department d, String actionType, String operator){
        String sql = "INSERT INTO department_history (department_id, faculty_id, name, operation_type, operated_by) VALUES (?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, d.getId());
            ps.setInt(2, d.getFacultyId());
            ps.setString(3, d.getName());
            ps.setString(4, actionType);
            ps.setString(5, operator);
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Department selectById(int id){
        String sql = "SELECT * FROM department WHERE id = ?";
        Department department = null;

        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                department.setFacultyId(rs.getInt("faculty_id"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
        return department;
    }
}