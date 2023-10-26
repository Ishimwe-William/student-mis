package com.bunsen.studentmis.dao.academicUnitDAO;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.academicUnit.Department;
import org.hibernate.Session;

import java.util.List;
import java.util.UUID;

public class DepartmentDAO {
    public DepartmentDAO() {
    }

    public void createDepartment(Department department) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Department getDepartmentById(UUID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Department department = session.get(Department.class, id);
        session.close();
        return department;
    }

    public List<Department> getAllDepartmentsByFaculty(UUID facultyId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Department d WHERE d.faculty.id = :facultyId", Department.class)
                    .setParameter("facultyId", facultyId)
                    .list();
        }
    }
}

