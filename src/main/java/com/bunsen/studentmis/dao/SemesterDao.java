package com.bunsen.studentmis.dao;

import com.bunsen.studentmis.model.Semester;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.UUID;

public class SemesterDao {
    private final SessionFactory sessionFactory;

    public SemesterDao() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public void createSemester(Semester semester) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(semester);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception appropriately in your application.
        }
    }

    public Semester getSemesterById(UUID semesterId) {
        Session session = sessionFactory.openSession();
        Semester semester = session.get(Semester.class, semesterId);
        session.close();
        return semester;
    }

    public List<Semester> getAllSemesters() {
        Session session = sessionFactory.openSession();
        List<Semester> semesters = session.createQuery("FROM Semester", Semester.class).list();
        session.close();
        return semesters;
    }

    public void updateSemester(Semester semester) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(semester);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception appropriately in your application.
        }
    }

    public void deleteSemester(UUID semesterId) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Semester semester = session.get(Semester.class, semesterId);
            if (semester != null) {
                session.delete(semester);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception appropriately in your application.
        }
    }
}

