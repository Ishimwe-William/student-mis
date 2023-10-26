package com.bunsen.studentmis.controller;

import com.bunsen.studentmis.dao.CourseDefinitionDao;
import com.bunsen.studentmis.dao.TeacherDao;
import com.bunsen.studentmis.model.CourseDefinition;
import com.bunsen.studentmis.model.Teacher;
import jakarta.servlet.RequestDispatcher;
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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action=req.getParameter("action");
       if("createCourse".equals(action)){
           CourseDefinition courseDefinition=new CourseDefinition();
           courseDefinition=courseRequest(req);
           dao.createCourseDefinition(courseDefinition);
           resp.sendRedirect(req.getContextPath() + "/courseDefinitionServlet");
       }else if("updated".equals(action)){

       }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if(action==null){
            List<CourseDefinition> courseDefinitions=dao.getAllCourses();
            req.setAttribute("courses", courseDefinitions);
            req.getRequestDispatcher("courseList.jsp").forward(req,resp);
        }else if("create".equals(action)){
            req.getRequestDispatcher("/courseDefinition.jsp").forward(req, resp);
        }
        else if("add_teachers".equals(action)){
            TeacherDao teacherDao=new TeacherDao();
            List<Teacher> teachers=teacherDao.getAllTeacher();
            req.setAttribute("teachers", teachers);
            UUID course_id = UUID.fromString(req.getParameter("course_id"));
            CourseDefinition course = dao.getCourseById(course_id);
            req.setAttribute("course", course);

            req.getRequestDispatcher("assignTeachersToCourses.jsp").forward(req,resp);
        }
        else if ("view".equals(action)) {
            String courseId = req.getParameter("course_id");
            CourseDefinition course = dao.getCourseById(UUID.fromString(courseId));

            if (course != null) {
                // Retrieve associated teachers
                Set<Teacher> teachers = course.getTeachers();

                req.setAttribute("course", course);
                req.setAttribute("teachers", teachers);

                // Forward to the viewCourse.jsp page
                req.getRequestDispatcher("/viewCourse.jsp").forward(req, resp);
            } else {
                // Handle error (course not found)
                resp.sendRedirect("error.jsp");
            }
        }

    }

    private CourseDefinition courseRequest(HttpServletRequest request) {
        CourseDefinition courseDefinition = new CourseDefinition();
        courseDefinition.setCode(request.getParameter("code").toUpperCase());
        courseDefinition.setName(request.getParameter("name"));
        courseDefinition.setDescription(request.getParameter("description"));
        return courseDefinition;
    }
}
