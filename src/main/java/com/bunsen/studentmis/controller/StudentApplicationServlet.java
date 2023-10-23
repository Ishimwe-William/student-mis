package com.bunsen.studentmis.controller;

import com.bunsen.studentmis.dao.StudentAdmissionDAO;
import com.bunsen.studentmis.model.ERegistrationStatus;
import com.bunsen.studentmis.model.Student;
import com.bunsen.studentmis.service.Mailer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;



@WebServlet("/studentApplication")
public class StudentApplicationServlet extends HttpServlet {
    private StudentAdmissionDAO dao=new StudentAdmissionDAO();
    private Student student;
    String action;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        action=req.getParameter("action");
        if("create_student".equals(action)){
            try {
                student=new Student();
                student = createStudentFromRequest(req);
                dao.saveStudent(student,req,resp);
                resp.sendRedirect(req.getContextPath() + "/studentApplication");
//            Mailer mailer=new Mailer();
//            String emailBody="Congratulation! Your registration is being reviewed.\n" +
//                    "We will send the verification once you're approved soon.\n" +
//                    "Please be patient!\n\n" +
//                    "Go bless you!";
//            mailer.sendEmail(student.getEmail(),emailBody);
            } catch (Exception e) {
                handleException(req, resp, e);
            }
        }else if (action.equals("update")) {
            student=new Student();
            String email = req.getParameter("email");
            student = dao.getStudentByEmail(email);
            student.setEmail(req.getParameter("newEmail"));
            student.setFullName(req.getParameter("name"));
            student.setDob(req.getParameter("dob"));
            student.setPhone(req.getParameter("phone"));
            student.setParent(req.getParameter("parent"));
            student.setParPhone(req.getParameter("parPhone"));
            dao.updateStudent(student);
            resp.sendRedirect(req.getContextPath() + "/studentApplication");
        }else if(action.equals("confirmDelete")){
            String student_email = req.getParameter("email");
            student=dao.getStudentByEmail(student_email);
            dao.deleteStudent(student);
            resp.sendRedirect(req.getContextPath() + "/studentApplication");
        }
    }

    private Student createStudentFromRequest(HttpServletRequest req) {
        student = new Student();
        student.setFullName(req.getParameter("theName"));
        student.setEmail(req.getParameter("theEmail").trim());
        student.setPhone(req.getParameter("the_phone"));
        student.setParent(req.getParameter("the_parent"));
        student.setDob(req.getParameter("the_dob"));
        student.setParPhone(req.getParameter("the_parent_phone"));
        student.setMajor(req.getParameter("majorFac"));
        student.setInvite(req.getParameter("the_invite"));
        student.setLikesArray(req.getParameterValues("how_like"));
        student.setMessage(req.getParameter("message"));
        student.setRegistrationStatus(ERegistrationStatus.PENDING);
        return student;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       action=request.getParameter("action");
       if(action==null){
           List<Student> students = dao.getAllApplicants();
           request.setAttribute("applied_students", students);
           request.getRequestDispatcher("/studentApplicationList.jsp").forward(request, response);
       }else if("create".equals(action)){
           request.getRequestDispatcher("/studentApplication.jsp").forward(request, response);
           return;
       }else if("edit".equals(action)){
            String student_email = (request.getParameter("student_email"));
            student=new Student();
               student = dao.getStudentByEmail(student_email);
               request.setAttribute("studentToEdit", student);
               request.getRequestDispatcher("/editStudentApplicant.jsp").forward(request, response);
       }else if("delete".equals(action)){
        String student_email = request.getParameter("student_email");
        student=new Student();
        student = dao.getStudentByEmail(student_email);
        request.setAttribute("studentToDelete", student);
        request.getRequestDispatcher("/deleteStudentConfirm.jsp").forward(request, response);
        return;
       }else if("approve".equals(action)){
        String student_email = request.getParameter("student_email");
        student=new Student();
        student = dao.getStudentByEmail(student_email);
        student.setRegistrationStatus(ERegistrationStatus.ADMITTED);
        dao.approveStudent(student);
        response.sendRedirect(request.getContextPath() + "/studentApplication");
        return;
       }
    }
    private void handleException(HttpServletRequest req, HttpServletResponse resp, Exception e)
            throws ServletException, IOException {
        e.printStackTrace();
        req.setAttribute("error", "An error occurred while saving the student data.");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
        dispatcher.forward(req, resp);
    }
}