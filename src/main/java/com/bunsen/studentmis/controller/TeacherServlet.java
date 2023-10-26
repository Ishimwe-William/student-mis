package com.bunsen.studentmis.controller;

import com.bunsen.studentmis.dao.TeacherDao;
import com.bunsen.studentmis.model.CourseDefinition;
import com.bunsen.studentmis.model.EQualification;
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

@WebServlet("/teacherServlet")
public class TeacherServlet extends HttpServlet {
    TeacherDao dao=new TeacherDao();
    Teacher teacher;
    String action;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action=request.getParameter("action");
        if(action==null){
                List<Teacher> teachers = dao.getAllTeacher();
                request.setAttribute("teachers", teachers);
                request.getRequestDispatcher("/teacherList.jsp").forward(request, response);
        }else{
        switch (action){
            case "create":
                request.getRequestDispatcher("/teacher.jsp").forward(request, response);
                break;
            case "edit":
                UUID teacherUUID = UUID.fromString((request.getParameter("teacher_id")));
                teacher=new Teacher();
                teacher = dao.getTeacherById(teacherUUID);
                request.setAttribute("teacherToEdit", teacher);
                request.getRequestDispatcher("/editTeacher.jsp").forward(request, response);
                break;
            case "delete":
                UUID teacher_id = UUID.fromString(request.getParameter("teacher_id"));
                teacher=new Teacher();
                teacher = dao.getTeacherById(teacher_id);
                request.setAttribute("teacherToDelete", teacher);
                request.getRequestDispatcher("/deleteTeacher.jsp").forward(request, response);
                break;
            case "view":
                String teacherId = request.getParameter("teacher_id");
                Teacher teacher = dao.getTeacherById(UUID.fromString(teacherId));
                if (teacher != null) {
                    // Retrieve associated courses
                    Set<CourseDefinition> courseSet = teacher.getCourseDefinitions();
                    request.setAttribute("teacher", teacher);
                    request.setAttribute("courses", courseSet);

                    // Forward to the viewTeacher.jsp
                    request.getRequestDispatcher("/viewTeacher.jsp").forward(request, response);
                }
                else {
                    // Handle error (course not found)
                    response.sendRedirect("error.jsp");
                }
            break;
        }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action=req.getParameter("action");
        if("create_teacher".equals(action)){
            try {
                teacher = new Teacher();
                teacher=teacherReq(req);
                dao.createTeacher(teacher);
                resp.sendRedirect(req.getContextPath() + "/teacherServlet");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.equals("update")) {
            UUID teacherId = UUID.fromString(req.getParameter("teacher_id"));
            teacher = dao.getTeacherById(teacherId);
            teacher=teacherReq(req);
            dao.updateTeacher(teacher);
            resp.sendRedirect(req.getContextPath() + "/teacherServlet");
        }
        else if(action.equals("confirmDelete")){
            UUID teacherId = UUID.fromString(req.getParameter("teacher_id"));
            teacher=dao.getTeacherById(teacherId);
            dao.deleteTeacher(teacher);
            resp.sendRedirect(req.getContextPath() + "/semesterServlet");
        }
    }

    private Teacher teacherReq(HttpServletRequest req) {
        teacher.setName(req.getParameter("name"));
        teacher.setDob(req.getParameter("dob"));
        teacher.setGender(req.getParameter("gender"));
        teacher.setDegree(EQualification.valueOf(req.getParameter("degree")));
        teacher.setEmail(req.getParameter("email").toLowerCase());
        teacher.setPhone(req.getParameter("phone"));
        String selectedType = req.getParameter("teacherType");
        if ("Tutor".equals(selectedType)) {
            teacher.setType("Tutor");
        } else if ("Assistant Tutor".equals(selectedType)) {
            teacher.setType("Assistant Tutor");
        }
        return teacher;
    }
}
