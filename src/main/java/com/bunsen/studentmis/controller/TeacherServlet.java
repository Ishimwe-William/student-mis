package com.bunsen.studentmis.controller;

import com.bunsen.studentmis.dao.TeacherDao;
import com.bunsen.studentmis.model.EQualification;
import com.bunsen.studentmis.model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
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
        }
        else if("create".equals(action)){
            request.getRequestDispatcher("/teacher.jsp").forward(request, response);
            return;
        }else if("edit".equals(action)){
            UUID teacherId = UUID.fromString((request.getParameter("teacher_id")));
            teacher=new Teacher();
            teacher = dao.getTeacherById(teacherId);
            request.setAttribute("teacherToEdit", teacher);
            request.getRequestDispatcher("/editTeacher.jsp").forward(request, response);
        }
        if("delete".equals(action)){
            UUID teacher_id = UUID.fromString(request.getParameter("teacher_id"));
            teacher=new Teacher();
            teacher = dao.getTeacherById(teacher_id);
            request.setAttribute("teacherToDelete", teacher);
            request.getRequestDispatcher("/deleteTeacher.jsp").forward(request, response);
            return;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
