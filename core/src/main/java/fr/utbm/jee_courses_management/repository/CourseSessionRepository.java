package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.CourseSession;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Class containing methods accessing to the database on the {@link CourseSession} entity. */
@AllArgsConstructor
public class CourseSessionRepository implements Serializable {

    /** {@link EntityManager} to allow requests and operations. */
    private final EntityManager entityManager;

    /** Repository to access to operations on {@link fr.utbm.jee_courses_management.entity.Client} */
    private ClientRepository clientRepository = null;

    /** (constructor)
     * Default constructor to initialize the {@link EntityManager}.
     */
    public CourseSessionRepository() {
        entityManager = EntityManagerFactory.getEntityManager();
    }

    /**
     * Get the {@link List} of all {@link CourseSession}.
     *
     * @return A {@link List} containing all {@link CourseSession}.
     */
    public List<CourseSession> getCourseSessions() {
        if (clientRepository == null)
            clientRepository = new ClientRepository();

        List<CourseSession> sessions = new ArrayList<>();
        for (Object result: entityManager.createQuery("from CourseSession").getResultList())
            if (result instanceof CourseSession) {
                CourseSession session = (CourseSession) result;
                session.setClientNumber(clientRepository.getNumberOfRegisteredClientsToTheCourseSession(session.getId()));
                sessions.add(session);
            }
        return sessions;
    }

    /**
     * Get the {@link List} of all {@link CourseSession} for the {@link fr.utbm.jee_courses_management.entity.Course} identified by the given id.
     *
     * @param courseId The id of the {@link fr.utbm.jee_courses_management.entity.Course} for which we want to retrieve all {@link CourseSession}
     * @return The {@link List} of all {@link CourseSession} for the identified {@link fr.utbm.jee_courses_management.entity.Course}
     */
    public List<CourseSession> getCourseSessionsByCourseId(Integer courseId) {
        if (clientRepository == null)
            clientRepository = new ClientRepository();

        List<CourseSession> sessions = new ArrayList<>();

        entityManager.createQuery(
                "from CourseSession session where session.course.id = :courseId")
                .setParameter("courseId", courseId)
                .getResultList().forEach(result -> {
                    if (result instanceof CourseSession) {
                        entityManager.detach(result);
                        CourseSession session = (CourseSession) result;
                        session.setClientNumber(clientRepository.getNumberOfRegisteredClientsToTheCourseSession(session.getId()));

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
    public CourseSession getCourseSessionById(Integer id) {
        if (clientRepository == null)
            clientRepository = new ClientRepository();

        CourseSession result = null;
        try {
            result = (CourseSession) entityManager.createQuery("from CourseSession session where session.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
            result.setClientNumber(clientRepository.getNumberOfRegisteredClientsToTheCourseSession(result.getId()));
        } catch (Exception exception) {
            System.out.println("Error during the research of the CourseSession " + id + ":");
            exception.printStackTrace();
        }
        return result;
    }

    /**
     * Check if a {@link CourseSession} identified by its id exists.
     *
     * @param sessionId The id of the {@link CourseSession} to test.
     * @return
     * <ul>
     *     <li>true if the {@link CourseSession} exists</li>
     *     <li>false else</li>
     * </ul>
     */
    public boolean doesCourseSessionExists(Integer sessionId) {
        try {
            Object courseSessionId = entityManager.createQuery(
                    "select session.id from CourseSession session where session.id = :id")
                    .setParameter("id", sessionId)
                    .getSingleResult();
            return courseSessionId instanceof Integer;

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
