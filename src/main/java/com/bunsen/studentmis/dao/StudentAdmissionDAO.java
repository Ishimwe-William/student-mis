package com.bunsen.studentmis.dao;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class StudentAdmissionDAO {
    public void saveStudent(Student student, HttpServletRequest req, HttpServletResponse resp) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Student existingStudent = viewStudent(student.getEmail());
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

    public void updateStudent(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        }
    }
    public void deleteStudent(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        }
    }
    public Student viewStudent(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Query the database to retrieve the student with the specified email
            Query<Student> query = session.createQuery("FROM Student WHERE email = :email", Student.class);
            query.setParameter("email", email);
            return query.uniqueResult(); // Returns the student or null if not found
        }
    }

    public List<Student> getAllApplicants() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("FROM Student", Student.class).list();
        session.close();
        return students;
    }

    public Student getStudentByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery("FROM Student WHERE email = :email", Student.class);
            query.setParameter("email", email);
            return query.uniqueResult(); // Returns the student or null if not found
        }
    }

    public void approveStudent(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        }
    }
}
