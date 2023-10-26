package com.bunsen.studentmis.dao.student;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.student.PendingStudent;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class StudentApplicationDAO {
    public void saveStudent(PendingStudent student, HttpServletRequest req, HttpServletResponse resp) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            PendingStudent existingStudent = viewStudent(student.getEmail());
            if (existingStudent == null) {
                session.save(student); // Save the new student
            } else {
                req.setAttribute("error", "A student with that email already exists!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
                dispatcher.forward(req, resp);
                return; // Return from the method to prevent further processing
            }

            transaction.commit();
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(PendingStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        }
    }
    public void deleteStudent(PendingStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        }
    }
    public PendingStudent viewStudent(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Query the database to retrieve the student with the specified email
            Query<PendingStudent> query = session.createQuery("FROM PendingStudent WHERE email = :email", PendingStudent.class);
            query.setParameter("email", email);
            return query.uniqueResult(); // Returns the student or null if not found
        }
    }

    public List<PendingStudent> getAllApplicants() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PendingStudent> students = session.createQuery("FROM PendingStudent", PendingStudent.class).list();
        session.close();
        return students;
    }

    public PendingStudent getStudentByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<PendingStudent> query = session.createQuery("FROM PendingStudent WHERE email = :email", PendingStudent.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }

}
