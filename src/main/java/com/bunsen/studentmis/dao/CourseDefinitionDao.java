package com.bunsen.studentmis.dao;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.CourseDefinition;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class CourseDefinitionDao {
    public void createCourseDefinition(CourseDefinition courseDefinition) {
        Transaction tr = null;
        try (Session ss = HibernateUtil.getSessionFactory().openSession()) {
            tr = ss.beginTransaction();
            ss.save(courseDefinition);
            tr.commit();
            ss.close();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<CourseDefinition> getAllCourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CourseDefinition> courseDefinitions = session.createQuery("FROM CourseDefinition ", CourseDefinition.class).list();
        session.close();
        return courseDefinitions;
    }

    public CourseDefinition getCourseById(UUID course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CourseDefinition courseDefinition = session.get(CourseDefinition.class, course_id);
        session.close();
        return courseDefinition;
    }

}
