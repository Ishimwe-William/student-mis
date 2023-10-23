package com.bunsen.studentmis.dao;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

import static com.bunsen.studentmis.HibernateUtil.getSessionFactory;

public class TeacherDao {
    public void createTeacher(Teacher teacher) {
        Transaction tr = null;
        try (Session ss = getSessionFactory().openSession()) {
            tr = ss.beginTransaction();
            ss.save(teacher);
            tr.commit();
            ss.close();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
    public void updateTeacher(Teacher teacher){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(teacher);
            transaction.commit();
        }
    }

    public List<Teacher> getAllTeacher() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Teacher> teachers = session.createQuery("FROM Teacher ", Teacher.class).list();
        session.close();
        return teachers;
    }

    public Teacher getTeacherById(UUID teacherId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Teacher teacher = session.get(Teacher.class, teacherId);
        session.close();
        return teacher;
    }

    public void deleteTeacher(Teacher teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(teacher);
            transaction.commit();
        }
    }
}
