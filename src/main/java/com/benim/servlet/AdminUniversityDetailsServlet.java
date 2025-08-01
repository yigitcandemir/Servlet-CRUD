package com.benim.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.benim.dao.CampusDAO;
import com.benim.dao.DepartmentDAO;
import com.benim.dao.FacultyDAO;
import com.benim.dao.UniversityDAO;
import com.benim.model.Campus;
import com.benim.model.Department;
import com.benim.model.Faculty;
import com.benim.model.University;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminUniversityDetails")
public class AdminUniversityDetailsServlet extends HttpServlet{
    private UniversityDAO universityDAO = new UniversityDAO();
    private FacultyDAO facultyDAO = new FacultyDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private CampusDAO campusDAO = new CampusDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int universityId = Integer.parseInt(request.getParameter("id"));

        try {
            University university = universityDAO.selectById(universityId);
            List<Campus> campuses = campusDAO.getCampusesByUniversityId(universityId);
            for(Campus campus : campuses){
                List<Faculty> faculties = facultyDAO.getFacultiesByCampusId(campus.getId());
                for(Faculty f : faculties){
                    List<Department> departments = departmentDAO.getDepartmentsByFacultyId(f.getId());
                    f.setDepartments(departments);
                }
                campus.setFaculties(faculties);
            }
            request.setAttribute("university", university);
            request.setAttribute("campuses", campuses);

            request.getRequestDispatcher("pages/admin-university-details.jsp").forward(request, response);
        }
        catch (SQLException e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
