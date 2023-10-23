package com.bunsen.studentmis.dao.student;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.student.AdmittedStudent;
import com.bunsen.studentmis.model.student.PendingStudent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentAdmissionDAO {

    public void saveAdmittedStudent(AdmittedStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            if (getStudentByEmail(student.getEmail()) == null) {
                session.save(student); // Save the new student
                transaction.commit();
            } else {
                throw new RuntimeException("A student with that email already exists!");
            }
        }
    }

    public void updateStudent(AdmittedStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        }
    }

    public void deleteStudent(AdmittedStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        }
    }

    public AdmittedStudent viewStudent(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<AdmittedStudent> query = session.createQuery("FROM AdmittedStudent WHERE email = :email", AdmittedStudent.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }

    public List<AdmittedStudent> getAllApplicants() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<AdmittedStudent> query = session.createQuery("FROM AdmittedStudent", AdmittedStudent.class);
            return query.list();
        }
    }

    public AdmittedStudent getStudentByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<AdmittedStudent> query = session.createQuery("FROM AdmittedStudent WHERE email = :email", AdmittedStudent.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }

    public void approveStudent(AdmittedStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        }
    }

    public void createAdmittedStudent(PendingStudent student) {
        // Perform validation checks on the PendingStudent entity here, if needed

        AdmittedStudent admittedStudent = new AdmittedStudent();
        admittedStudent.setFullName(student.getFullName());
        admittedStudent.setEmail(student.getEmail());
        admittedStudent.setPhone(student.getPhone());
        admittedStudent.setParent(student.getParent());
        admittedStudent.setDob(student.getDob());
        admittedStudent.setParPhone(student.getParPhone());
        admittedStudent.setDepartment(student.getDepartment());

        // Check if the student with the same email already exists
        if (getStudentByEmail(admittedStudent.getEmail()) != null) {
            throw new RuntimeException("A student with that email already exists!");
        }

        // Start a transaction to save the AdmittedStudent
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(admittedStudent);
            transaction.commit();
        } catch (Exception e) {
            // Handle any exceptions that may occur during the transaction
            throw new RuntimeException("Error while creating admitted student: " + e.getMessage(), e);
        }
    }
}
