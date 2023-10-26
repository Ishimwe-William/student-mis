package com.bunsen.studentmis.controller.academicUnit;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.dao.academicUnitDAO.DepartmentDAO;
import com.bunsen.studentmis.dao.academicUnitDAO.FacultyDAO;
import com.bunsen.studentmis.dao.academicUnitDAO.ProgrammeDAO;
import com.bunsen.studentmis.model.academicUnit.Department;
import com.bunsen.studentmis.model.academicUnit.Faculty;
import com.bunsen.studentmis.model.academicUnit.Programme;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet("/academicUnitHome")
public class AcademicUnit extends HttpServlet {
    private ProgrammeDAO programmeDAO;
    private FacultyDAO facultyDAO;
    private DepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        programmeDAO = new ProgrammeDAO(sessionFactory);
        facultyDAO = new FacultyDAO(sessionFactory);
        departmentDAO = new DepartmentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action: " + action);
        if (action == null) {
            // Handle the case where 'action' is null
            // You might want to show an error message or perform some action here.
            return;
        }

        switch (action) {
            case "create_programme": {
                String code = request.getParameter("prog_code").toUpperCase();
                String name = request.getParameter("prog_name").toUpperCase();

                Programme programme = new Programme();
                programme.setCode(code);
                programme.setName(name);
                programmeDAO.createProgramme(programme);
                break;
            }
            case "create_faculty": {
                String code = request.getParameter("fac_code").toUpperCase();
                String name = request.getParameter("fac_name").toUpperCase();
                String selectedProgramId = request.getParameter("programme_id");

                if (selectedProgramId != null && !selectedProgramId.isEmpty()) {
                    UUID programId = UUID.fromString(selectedProgramId);
                    Programme programme = programmeDAO.getProgrammeById(programId);
                    System.out.println(programme.getCode());

                    Faculty faculty = new Faculty();
                    faculty.setCode(code);
                    faculty.setName(name);
                    faculty.setProgramme(programme);
                    facultyDAO.createFaculty(faculty);
                }
                break;
            }
            case "create_department": {
                String name = request.getParameter("dep_name").toUpperCase();
                String code = request.getParameter("dep_code").toUpperCase();
                String selectedFacultyId = request.getParameter("faculty_id");
                System.out.println(selectedFacultyId);

                if (selectedFacultyId != null && !selectedFacultyId.isEmpty()) {
                    UUID facultyId = UUID.fromString(selectedFacultyId);
                    Faculty faculty = facultyDAO.getFacultyById(facultyId);

                    if (faculty != null) {
                        Department department = new Department();
                        department.setCode(code);
                        department.setName(name);
                        department.setFaculty(faculty);
                        departmentDAO.createDepartment(department);
                    }
                }
                break;
            }
        }
        request.getRequestDispatcher("/academicUnitHome.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("operation");
        Gson json;

        if (action == null) {
            request.getRequestDispatcher("/academicUnitHome.jsp").forward(request, response);
            return;
        }

        try (PrintWriter ignored = response.getWriter()) {
            switch (action) {
                case "get_programs":
                    List<Programme> programmes = programmeDAO.getAllProgrammes();
                    json = new Gson();
                    String programList = json.toJson(programmes);
                    response.setContentType("text/html");
                    response.getWriter().write(programList);
                    break;
                case "get_faculty":
                    String selectedProgramId = request.getParameter("selectedProgramId");
                    if (selectedProgramId != null && !selectedProgramId.isEmpty()) {
                        UUID programmeId = UUID.fromString(selectedProgramId);
                        List<Faculty> faculties = facultyDAO.getAllFacultiesByProgramme(programmeId);
                        json = new Gson();
                        String facultyList = json.toJson(faculties);
                        response.setContentType("text/html");
                        response.getWriter().write(facultyList);
                    }
                    break;
                case "get_departments":
                    String selectedFacultyId = request.getParameter("selectedFacultyId");
                    if (selectedFacultyId != null && !selectedFacultyId.isEmpty()) {
                        UUID facultyId = UUID.fromString(selectedFacultyId);
                        List<Department> departments = departmentDAO.getAllDepartmentsByFaculty(facultyId);
                        json = new Gson();
                        String departmentList = json.toJson(departments);
                        response.setContentType("text/html");
                        response.getWriter().write(departmentList);
                    }
                    break;
            }
        }
    }
}
