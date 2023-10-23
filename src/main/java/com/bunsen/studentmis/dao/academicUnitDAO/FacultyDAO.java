package com.bunsen.studentmis.dao.academicUnitDAO;


import com.bunsen.studentmis.model.academicUnit.Faculty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

public class FacultyDAO {
    private SessionFactory sessionFactory;

    public FacultyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Faculty createFaculty(Faculty faculty) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(faculty);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return faculty;
    }

    public Faculty getFacultyById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Faculty.class, id);
        }
    }

    public void updateFaculty(Faculty faculty) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(faculty);
            session.getTransaction().commit();
        }
    }

    public void deleteFaculty(Faculty faculty) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(faculty);
            session.getTransaction().commit();
        }
    }

    public List<Faculty> getAllFaculties() {
        Session session = sessionFactory.openSession();
        List<Faculty> faculties = session.createQuery("FROM Faculty ", Faculty.class).list();
        session.close();
        return faculties;
    }
    public List<Faculty> getAllFacultiesByProgramme(UUID programId) {
        try (Session session = sessionFactory.openSession()) {
            List<Faculty> faculties = session.createQuery("FROM Faculty f WHERE f.programme.id = :programId", Faculty.class)
                    .setParameter("programId", programId)
                    .list();
            return faculties;
        }
    }

}

