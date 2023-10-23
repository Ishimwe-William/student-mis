package com.bunsen.studentmis.controller;

import com.bunsen.studentmis.dao.CourseDefinitionDao;
import com.bunsen.studentmis.model.CourseDefinition;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/courseDefinitionServlet")
public class CourseDefinitionServlet extends HttpServlet {

    CourseDefinitionDao dao=new CourseDefinitionDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseDefinition newCourseDescription = dao.createCourseDefinition(semesterRequest(req));
        if (newCourseDescription == null) {
            req.setAttribute("error", "Error while creating course description");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("success.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if(action==null){
            req.getRequestDispatcher("courseDefinition.jsp").forward(req,resp);
        }
    }

    private CourseDefinition semesterRequest(HttpServletRequest request) {
        CourseDefinition courseDefinition = new CourseDefinition();
        courseDefinition.setCode(request.getParameter("code").toUpperCase());
        courseDefinition.setName(request.getParameter("name"));
        courseDefinition.setDescription(request.getParameter("description"));
        return courseDefinition;
    }
}
