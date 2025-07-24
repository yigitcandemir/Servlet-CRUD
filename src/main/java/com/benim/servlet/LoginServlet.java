package com.benim.servlet;

import java.io.IOException;

import com.benim.dao.AdminDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private AdminDAO adminDAO = new AdminDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(adminDAO.validate(username, password)){
            HttpSession session = request.getSession();
            session.setAttribute("admin", username);
            response.sendRedirect("pages/admin-panel.jsp");
        }
        else{
            response.sendRedirect("pages/login.jsp?error=true");
        }
    }
}
