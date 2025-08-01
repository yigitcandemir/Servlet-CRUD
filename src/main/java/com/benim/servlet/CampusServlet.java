package com.benim.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

@WebServlet("/CampusServlet")
public class CampusServlet extends HttpServlet{
    private CampusDAO campusDAO = new CampusDAO();
    private FacultyDAO facultyDAO = new FacultyDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int universityId = Integer.parseInt(request.getParameter("universityId"));

        try {
            List<Campus> campusList = campusDAO.getCampusesByUniversityId(universityId);
            for(Campus campus : campusList){
                List<Faculty> faculties = facultyDAO.getFacultiesByCampusId(campus.getId());
                for(Faculty faculty : faculties){
                    List<Department> departments = departmentDAO.getDepartmentsByFacultyId(faculty.getId());
                    faculty.setDepartments(departments);
                }
                campus.setFaculties(faculties);
            }
            request.setAttribute("campusList", campusList);
            request.getRequestDispatcher("pages/campus-list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
