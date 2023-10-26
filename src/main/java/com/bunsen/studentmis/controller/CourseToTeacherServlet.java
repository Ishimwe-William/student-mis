package com.bunsen.studentmis.controller;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.CourseDefinition;
import com.bunsen.studentmis.model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/teacherAssignment")
public class CourseToTeacherServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("assignTeachersToCourse")) {
            String[] teacherIds = request.getParameterValues("teacherIds");
            String courseId = request.getParameter("courseId");

            if (teacherIds != null && courseId != null) {
                try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {
                    Transaction transaction = hibernateSession.beginTransaction();

                    CourseDefinition course = hibernateSession.get(CourseDefinition.class, UUID.fromString(courseId));

                    if (course != null) {
                        for (String teacherId : teacherIds) {
                            Teacher teacher = hibernateSession.get(Teacher.class, UUID.fromString(teacherId));
                            if (teacher != null) {
                                teacher.addCourseDefinition(course);
                            }
                        }

                        transaction.commit();
                        response.sendRedirect("/courseDefinitionServlet");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response.sendRedirect("error.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
