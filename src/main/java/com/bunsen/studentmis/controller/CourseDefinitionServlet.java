package com.bunsen.studentmis.controller;

import com.bunsen.studentmis.dao.CourseDefinitionDao;
import com.bunsen.studentmis.dao.TeacherDao;
import com.bunsen.studentmis.model.CourseDefinition;
import com.bunsen.studentmis.model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@WebServlet("/courseDefinitionServlet")
public class CourseDefinitionServlet extends HttpServlet {

    CourseDefinitionDao dao=new CourseDefinitionDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       String action=req.getParameter("action");
       if("createCourse".equals(action)){
           new CourseDefinition();
           CourseDefinition courseDefinition = courseRequest(req);
           dao.createCourseDefinition(courseDefinition);
           resp.sendRedirect(req.getContextPath() + "/courseDefinitionServlet");
       }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CourseDefinition course;
        if (action==null) {
            List<CourseDefinition> courseDefinitions = dao.getAllCourses();
            req.setAttribute("courses", courseDefinitions);
            req.getRequestDispatcher("courseList.jsp").forward(req, resp);
        } else {
            switch (action) {
                case "create":
                    req.getRequestDispatcher("/courseDefinition.jsp").forward(req, resp);
                    break;
                case "add_teachers":
                    TeacherDao teacherDao = new TeacherDao();
                    List<Teacher> teachers = teacherDao.getAllTeacher();
                    req.setAttribute("teachers", teachers);
                    UUID course_id = UUID.fromString(req.getParameter("course_id"));
                    course = dao.getCourseById(course_id);
                    req.setAttribute("course", course);
                    req.getRequestDispatcher("assignTeachersToCourses.jsp").forward(req, resp);
                    break;
                case "view":
                    String courseId = req.getParameter("course_id");
                    course = dao.getCourseById(UUID.fromString(courseId));

                    if (course != null) {
                        // Retrieve associated teachers
                        Set<Teacher> teacherSet = course.getTeachers();

                        req.setAttribute("course", course);
                        req.setAttribute("teachers", teacherSet);

                        // Forward to the viewCourse.jsp page
                        req.getRequestDispatcher("/viewCourse.jsp").forward(req, resp);
                    } else {
                        // Handle error (course not found)
                        resp.sendRedirect("error.jsp");
                    }
                    break;
            }
        }
    }
    private CourseDefinition courseRequest(HttpServletRequest request) {
        CourseDefinition courseDefinition = new CourseDefinition();
        courseDefinition.setCode(request.getParameter("code").trim().toUpperCase());
        courseDefinition.setName(request.getParameter("name"));
        courseDefinition.setCredits(Integer.valueOf(request.getParameter("credits")));
        System.out.println(Integer.valueOf(request.getParameter("credits")));
        courseDefinition.setDescription(request.getParameter("description"));
        return courseDefinition;
    }
}
