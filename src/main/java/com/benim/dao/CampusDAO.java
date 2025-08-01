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

    private static final String INSERT_SQL = "INSERT INTO campus(university_id, name,city, district, address) values (?,?,?,?,?) ";
    private static final String UPDATE_SQL = "UPDATE campus SET name = ?, city = ?, district = ?, address = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM campus WHERE id = ?";

    public void insert(Campus c) throws SQLException{
        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)){
            ps.setInt(1, c.getUniversityId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getCity());
            ps.setString(4, c.getDistrict());
            ps.setString(5, c.getAddress());
            ps.executeUpdate();
        }
    }

    public void update(Campus c) throws SQLException{
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setString(1, c.getName());
            ps.setString(2, c.getCity());
            ps.setString(3, c.getDistrict());
            ps.setString(4, c.getAddress());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException{
        try(Connection conn = DBUtil.getConnection("universities"); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Campus> getCampusesByUniversityId(int universityId) throws SQLException {
        List<Campus> list = new ArrayList<>();
        String sql = "SELECT * FROM campus WHERE university_id = ?";

        try (Connection conn = DBUtil.getConnection("universities"); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
}