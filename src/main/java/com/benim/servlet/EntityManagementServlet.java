package com.benim.servlet;

import java.io.IOException;

import com.benim.dao.CampusDAO;
import com.benim.dao.DepartmentDAO;
import com.benim.dao.FacultyDAO;
import com.benim.model.Campus;
import com.benim.model.Department;
import com.benim.model.Faculty;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/EntityManagement")
public class EntityManagementServlet extends HttpServlet{
    private CampusDAO campusDAO = new CampusDAO();
    private FacultyDAO facultyDAO = new FacultyDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entity = request.getParameter("entity");
        String action = request.getParameter("action");

        try {
            switch(entity){
                case "campus":
                    handleCampus(request, response, action);
                    break;
                case "faculty":
                    handleFaculty(request, response, action);
                    break;
                case "department":
                    handleDepartment(request, response, action);
                    break;
                default:
                    response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
    

    private void handleCampus(HttpServletRequest request, HttpServletResponse response, String action) throws Exception{
        int universityId = Integer.parseInt(request.getParameter("universityId"));
        if(action.equals("add")){
            Campus c = new Campus(0, universityId, request.getParameter("name"),request.getParameter("city"),request.getParameter("district"),request.getParameter("address"));
            campusDAO.insert(c);
        }
        else if (action.equals("update")){
            Campus c = new Campus(Integer.parseInt(request.getParameter("id")), universityId,request.getParameter("name"),request.getParameter("city"),request.getParameter("district"),request.getParameter("address"));
            campusDAO.update(c);
        }
        else if (action.equals("delete")){
            campusDAO.delete(Integer.parseInt(request.getParameter("id")));
        }
        response.sendRedirect("AdminUniversityDetails?id=" + universityId);
    }

    private void handleFaculty(HttpServletRequest request, HttpServletResponse response, String action) throws Exception{
        int campusId = Integer.parseInt(request.getParameter("campusId"));
        int universityId = Integer.parseInt(request.getParameter("universityId"));
        String name = request.getParameter("name");
        String telephone = request.getParameter("telephone");
        String dean = request.getParameter("dean");

        if(action.equals("add")){
            Faculty f = new Faculty(0,name,campusId,telephone,dean);
            facultyDAO.insert(f);
        }
        else if(action.equals("update")){
            Faculty f = new Faculty(Integer.parseInt(request.getParameter("id")), name, campusId, telephone, dean);
            facultyDAO.update(f);
        }
        else if (action.equals("delete")){
            facultyDAO.delete(Integer.parseInt(request.getParameter("id")));
        }
        response.sendRedirect("AdminUniversityDetails?id=" + universityId);
    }

    private void handleDepartment(HttpServletRequest request, HttpServletResponse response, String action) throws Exception{
        int facultyId = Integer.parseInt(request.getParameter("facultyId"));
        int universityId = Integer.parseInt(request.getParameter("universityId"));

        if(action.equals("add")){
            Department d = new Department(0,facultyId,request.getParameter("name"));
            departmentDAO.insert(d);
        }
        else if (action.equals("update")){
            Department d = new Department(Integer.parseInt(request.getParameter("id")), facultyId, request.getParameter("name"));
            departmentDAO.update(d);
        }
        else if (action.equals("delete")){
            departmentDAO.delete(Integer.parseInt(request.getParameter("id")));
        }
        response.sendRedirect("AdminUniversityDetails?id=" + universityId);
    }
}
