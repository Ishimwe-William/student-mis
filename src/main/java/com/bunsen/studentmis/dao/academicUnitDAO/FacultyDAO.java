package com.bunsen.studentmis.dao.academicUnitDAO;


import com.bunsen.studentmis.model.academicUnit.Faculty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

public class FacultyDAO {
    private final SessionFactory sessionFactory;

    public FacultyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createFaculty(Faculty faculty) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(faculty);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Faculty getFacultyById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Faculty.class, id);
        }
    }

    public List<Faculty> getAllFacultiesByProgramme(UUID programId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Faculty f WHERE f.programme.id = :programId", Faculty.class)
                    .setParameter("programId", programId)
                    .list();
        }
    }

}

