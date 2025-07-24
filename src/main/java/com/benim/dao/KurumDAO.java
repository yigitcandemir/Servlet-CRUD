package com.benim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.benim.model.Kurum;

public class KurumDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/kurum_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345";

    private static final String SELECT_ALL = "SELECT * FROM kurumlar";
    private static final String SELECT_BY_NAME = "SELECT * FROM kurumlar WHERE name LIKE ?";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

    public List<Kurum> selectAll() throws SQLException {
        List<Kurum> liste = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(new Kurum(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("city"),
                        rs.getString("website")));
            }
        }
        return liste;
    }

    public List<Kurum> selectByName(String name) throws SQLException {
        List<Kurum> kurumlar = new ArrayList<>();
        try( Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_NAME)){
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                kurumlar.add(new Kurum(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("city"),
                    rs.getString("website")));
            }
        }
        return kurumlar;
    }


}