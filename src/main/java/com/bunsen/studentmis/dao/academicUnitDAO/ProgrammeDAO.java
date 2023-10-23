package com.bunsen.studentmis.dao.academicUnitDAO;

import com.bunsen.studentmis.model.academicUnit.Programme;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class ProgrammeDAO {
    private SessionFactory sessionFactory;

    public ProgrammeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Programme createProgramme(Programme programme) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(programme);
            transaction.commit();
            return programme;
        } catch (Exception e) {
            e.printStackTrace();
            // Consider handling the exception more gracefully, e.g., by throwing a custom exception
            return null;
        }
    }

    public Programme getProgrammeById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Programme.class, id);
        }
    }

    public void updateProgramme(Programme programme) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(programme);
            transaction.commit();
        }
    }

    public void deleteProgramme(Programme programme) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(programme);
            transaction.commit();
        }
    }

    public List<Programme> getAllProgrammes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Programme", Programme.class).list();
        }
    }
}
