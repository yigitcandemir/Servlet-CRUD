package com.benim.servlet;

import java.io.IOException;
import java.util.List;

import com.benim.api.ApiClient;
import com.benim.api.University;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UniversityServlet")
public class UniversityServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nameFilter = request.getParameter("name");
        List<University> turkishUnis = ApiClient.getTurkishUniversities(nameFilter);
        request.setAttribute("universities", turkishUnis);
        request.getRequestDispatcher("pages/Universities.jsp").forward(request, response);
    }
    
}