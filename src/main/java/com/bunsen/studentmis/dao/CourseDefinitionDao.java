package com.bunsen.studentmis.dao;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.CourseDefinition;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CourseDefinitionDao {
    public CourseDefinition createCourseDefinition(CourseDefinition courseDefinition) {
        Transaction tr = null;
        try (Session ss = HibernateUtil.getSessionFactory().openSession()) {
            tr = ss.beginTransaction();
            ss.save(courseDefinition);
            tr.commit();
            ss.close();
            return courseDefinition;
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
    public void updateSemester(CourseDefinition semester) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(semester);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception appropriately in your application.
        }
}}
