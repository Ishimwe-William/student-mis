package com.bunsen.studentmis.controller;

import com.bunsen.studentmis.dao.SemesterDao;
import com.bunsen.studentmis.model.Semester;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@WebServlet("/semesterServlet")
public class SemesterServlet extends HttpServlet {
    private SemesterDao semesterDao;
    Semester semester;

    public void init() {
        semesterDao = new SemesterDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action==null) {

            List<Semester> semesters = semesterDao.getAllSemesters();
            request.setAttribute("semesters", semesters);
            request.getRequestDispatcher("/semesterList.jsp").forward(request, response);
        } else {
            switch (action){
            case "create":
            request.getRequestDispatcher("/createSemesterForm.jsp").forward(request, response);
            break;
            case "edit":
                UUID semesterId = UUID.fromString(request.getParameter("semester_id"));
            semester = semesterDao.getSemesterById(semesterId);
            request.setAttribute("semesterToEdit", semester);
            request.getRequestDispatcher("/editSemesterForm.jsp").forward(request, response);
            break;
            case "delete":
            semesterId = UUID.fromString(request.getParameter("semester_id"));
            semester = semesterDao.getSemesterById(semesterId);
            request.setAttribute("semesterToDelete", semester);
            request.getRequestDispatcher("/deleteSemesterConfirm.jsp").forward(request, response);
            break;
        }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                semesterDao.createSemester(semesterRequest(request));
                response.sendRedirect(request.getContextPath() + "/semesterServlet");
                break;
            case "update": {
                UUID semesterId = UUID.fromString(request.getParameter("semester_id"));
                semester = semesterDao.getSemesterById(semesterId);
                semesterDao.updateSemester(semesterRequest(request));
                response.sendRedirect(request.getContextPath() + "/semesterServlet");
                break;
            }
            case "confirmDelete": {
                UUID semesterId = UUID.fromString(request.getParameter("semester_id"));
                semesterDao.deleteSemester(semesterId);
                response.sendRedirect(request.getContextPath() + "/semesterServlet");
                break;
            }
        }
    }
    private Semester semesterRequest(HttpServletRequest request){
        Semester semester=new Semester();
        semester.setName(request.getParameter("name"));
        semester.setStart_date(request.getParameter("start_date"));
        semester.setEnd_date(request.getParameter("end_date"));
        return semester;
    }
}