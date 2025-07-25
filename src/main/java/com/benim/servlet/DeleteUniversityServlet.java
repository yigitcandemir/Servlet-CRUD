package com.benim.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.benim.dao.UniversityDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteUniversity")
public class DeleteUniversityServlet extends HttpServlet{

    private UniversityDAO universityDAO = new UniversityDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("admin") == null){
            response.sendRedirect("pages/login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));

        try{
            universityDAO.delete(id);
            response.sendRedirect("UniversityServlet");
        }
        catch(SQLException e){
            e.printStackTrace();
            response.sendRedirect("UniversityServlet?error=deleteFailed");
        }
    }
    
}
