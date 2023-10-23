package com.bunsen.studentmis.dao.academicUnitDAO;

import com.bunsen.studentmis.model.academicUnit.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

public class DepartmentDAO {
    private SessionFactory sessionFactory;

    public DepartmentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Department createDepartment(Department department) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return department;
    }

    public Department getDepartmentById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Department.class, id);
        }
    }

    public void updateDepartment(Department department) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        }
    }

    public void deleteDepartment(Department department) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
        }
    }
    public List<Department> getAllDepartments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department", Department.class).list();
        }
    }
}

