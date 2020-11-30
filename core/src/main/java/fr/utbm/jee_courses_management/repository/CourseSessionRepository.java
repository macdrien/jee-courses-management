package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.entity.CourseSession;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseSessionRepository implements Serializable {

    private final EntityManager entityManager;

    public CourseSessionRepository() {
        entityManager = EntityManagerFactory.getEntityManager();
    }

    public List<CourseSession> getCourseSessions() {
        List<CourseSession> sessions = new ArrayList<>();
        for (Object result: entityManager.createQuery("from CourseSession").getResultList())
            if (result instanceof CourseSession)
                sessions.add((CourseSession) result);
        return sessions;
    }

    public List<CourseSession> getCourseSessionsByCourseId(Integer courseId) {
        List<CourseSession> sessions = new ArrayList<>();
        for (Object result:
                entityManager.createQuery("from CourseSession session where session.course.id = :courseId")
                        .setParameter("courseId", courseId).getResultList())
            if (result instanceof CourseSession)
                sessions.add((CourseSession) result);
        return sessions;
    }
}
