package com.benim.servlet;

import java.io.IOException;

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
        if(session == null ||session.getAttribute("admin") == null){
            response.sendRedirect("pages/login.jsp");
            return;
        }
        String name = request.getParameter("name");
        String website = request.getParameter("website");

        University uni = new University(name,website);
        universityDAO.insert(uni);

        response.sendRedirect("UniversityServlet");
    }
}
