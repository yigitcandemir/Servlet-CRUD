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
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);

        String idString = request.getParameter("id");


        if(idString == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID bulunamadı");
            return;
        }
        try{
            int id = Integer.parseInt(idString);
            universityDAO.delete(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        catch(NumberFormatException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID geçersiz.");
        }
        catch (SQLException e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Veri silinirken hata oluştu");
        }
    }
    
}
