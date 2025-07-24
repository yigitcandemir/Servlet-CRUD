package com.benim.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.benim.dao.KurumDAO;
import com.benim.model.Kurum;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/KurumServlet")   // ✅ Tarayıcıdan çağıracağın URL
public class KurumServlet extends HttpServlet {
    private KurumDAO dao;

    public void init() {
        dao = new KurumDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Kurum> liste = dao.selectAll();
            request.setAttribute("liste", liste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("pages/list.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
}
