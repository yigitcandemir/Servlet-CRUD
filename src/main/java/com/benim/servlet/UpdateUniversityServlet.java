package com.benim.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.benim.dao.UniversityDAO;
import com.benim.model.University;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateUniversity")
public class UpdateUniversityServlet extends HttpServlet{
    private UniversityDAO universityDAO = new UniversityDAO();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String website = request.getParameter("website");

        try {
            University uni = new University(id,name, website);
            universityDAO.update(uni);  
            response.sendRedirect("UniversityServlet"); 
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("pages/update-university.jsp?error=unknown&id=" + id);
        }
    }
}
