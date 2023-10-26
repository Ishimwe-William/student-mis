package com.bunsen.studentmis.dao.academicUnitDAO;

import com.bunsen.studentmis.model.academicUnit.Programme;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class ProgrammeDAO {
    private final SessionFactory sessionFactory;

    public ProgrammeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createProgramme(Programme programme) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(programme);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Consider handling the exception more gracefully, e.g., by throwing a custom exception
        }
    }

    public Programme getProgrammeById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Programme.class, id);
        }
    }

    public List<Programme> getAllProgrammes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Programme", Programme.class).list();
        }
    }
}
