package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.entity.CourseSession;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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

        entityManager.createQuery(
                "from CourseSession session where session.course.id = :courseId")
                .setParameter("courseId", courseId)
                .getResultList().forEach(result -> {
                    if (result instanceof CourseSession) {
                        entityManager.detach(result);
                        CourseSession session = (CourseSession) result;

                        /*
                         * Set to null to avoid StackOverflowException.
                         * We know the course so we don't need it. That stop the loop between the Course and CourseSession.
                         */
                        session.setCourse(null);

                        sessions.add(session);
                    }
                });

        return sessions;
    }

    /**
     * Get a {@link CourseSession} identified by the given id.
     *
     * @param id The id of the searched {@link CourseSession}.
     * @return The found {@link CourseSession}.<br/>
     *          Null if the {@link CourseSession} does not exist or if an error occurred.
     */
    // TODO Test
    public CourseSession getCourseSessionById(Integer id) {
        CourseSession result = null;
        try {
            result = (CourseSession) entityManager.createQuery("from CourseSession session where session.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception exception) {
            System.out.println("Error during the research of the CourseSession " + id + ":");
            exception.printStackTrace();
        }
        return result;
    }
}
