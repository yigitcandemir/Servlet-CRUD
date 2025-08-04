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


@WebServlet("/CreateUniversity")
public class CreateUniversityServlet extends HttpServlet{
    private UniversityDAO universityDAO = new UniversityDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        String operator = (session != null) ? (String) session.getAttribute("username") : "anonymous";
        String name = request.getParameter("name");
        String website = request.getParameter("website");

        try {
            University uni = new University(name, website);
            universityDAO.insert(uni, operator);
            response.sendRedirect("UniversityServlet");
        } 
        catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("pages/create-university.jsp?error=unknown");
        }
    }
}
