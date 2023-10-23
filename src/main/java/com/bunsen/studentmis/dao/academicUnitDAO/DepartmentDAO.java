package com.bunsen.studentmis.dao.academicUnitDAO;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.Semester;
import com.bunsen.studentmis.model.academicUnit.Department;
import com.bunsen.studentmis.model.academicUnit.Faculty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

public class DepartmentDAO {
    public DepartmentDAO() {
    }

    public Department createDepartment(Department department) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Department department = session.get(Department.class, id);
        session.close();
        return department;
    }

    public void updateDepartment(Department department) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        }
    }

    public void deleteDepartment(Department department) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
        }
    }
    public List<Department> getAllDepartments() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Department", Department.class).list();
        }
    }

    public List<Department> getAllDepartmentsByFaculty(UUID facultyId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Department> departments = session.createQuery("FROM Department d WHERE d.faculty.id = :facultyId", Department.class)
                    .setParameter("facultyId", facultyId)
                    .list();
            return departments;
        }
    }
}

