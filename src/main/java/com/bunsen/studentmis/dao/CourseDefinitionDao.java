package com.bunsen.studentmis.dao;

import com.bunsen.studentmis.HibernateUtil;
import com.bunsen.studentmis.model.CourseDefinition;
import com.bunsen.studentmis.model.Semester;
import com.bunsen.studentmis.model.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CourseDefinitionDao {
    TeacherDao teacherDao=new TeacherDao();
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

    public void updateCourseDefinition(CourseDefinition course, Teacher teacher) {
        if (course != null && teacher != null) {
            Set<Teacher> teachers = course.getTeachers();
            // Check if the teacher is not already in the course's list of teachers
            if (!teachers.contains(teacher)) {
                teachers.add(teacher);
                course.setTeachers(teachers);
                Transaction tr = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    tr = session.beginTransaction();
                    session.merge(course); // Use merge to update the course
                    tr.commit();
                } catch (Exception e) {
                    if (tr != null) {
                        tr.rollback();
                    }
                    e.printStackTrace();
                    // Handle the exception as needed
                }
            }
        }
    }

    public void updateCourse(CourseDefinition course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Merge or update the course
            session.merge(course); // You can also use session.update(course) if you are sure it's not detached

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            session.close();
        }
    }

}
