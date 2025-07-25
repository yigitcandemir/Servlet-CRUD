package com.benim.servlet;

import java.io.IOException;
import java.util.List;

import com.benim.dao.UniversityDAO;
import com.benim.model.University;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UniversityServlet")
public class UniversityServlet extends HttpServlet {
    private UniversityDAO universityDAO = new UniversityDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("search");
        List<University> dbList;

        if (search != null && !search.trim().isEmpty()) {
            dbList = universityDAO.searchByName(search.trim());  
        } else {
            dbList = universityDAO.selectAll();         
        }

        request.setAttribute("dbUniversities", dbList);
        request.getRequestDispatcher("pages/universities.jsp").forward(request, response);
    }
}

