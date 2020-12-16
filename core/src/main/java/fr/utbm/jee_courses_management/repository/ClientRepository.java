package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Class containing methods accessing to the database on the {@link Client} entity. */
@AllArgsConstructor
public class ClientRepository implements Serializable {

    /** {@link EntityManager} to allow requests and operations. */
    private EntityManager entityManager;

    /** A repository to access to operations on {@link fr.utbm.jee_courses_management.entity.CourseSession} */
    private CourseSessionRepository courseSessionRepository = null;

    /** (constructor)
     * Default constructor to initialize the {@link EntityManager}.
     */
    public ClientRepository() {
        entityManager = EntityManagerFactory.getEntityManager();
    }

    /**
     * Get a {@link Client} identified by his id.
     *
     * @param id The id of the searched {@link Client}.
     * @return The found {@link Client}.<br/>
     *          Null if the {@link Client} does not exist or if an error occurred.
     */
    public Client getClientById(Integer id) {
        Client result = null;
        try {
            result = (Client) entityManager.createQuery(
                    "from Client client where client.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return result;
    }

    /**
     * Get a {@link List} of {@link Client} who have been registered to the given {@link fr.utbm.jee_courses_management.entity.CourseSession}.
     * @param sessionId The id of the {@link fr.utbm.jee_courses_management.entity.CourseSession}.
     * @return
     * <ul>
     *     <li>A {@link List} of registered {@link Client}.</li>
     *     <li>An empty {@link List} if there is no registered {@link Client}.</li>
     *     <li>Will return null if the {@link fr.utbm.jee_courses_management.entity.CourseSession} does not exist.</li>
     * </ul>
     */
    public List<Client> getClientsBySessionId(Integer sessionId) {
        if (courseSessionRepository == null)
            courseSessionRepository = new CourseSessionRepository();

        if (!courseSessionRepository.doesCourseSessionExists(sessionId))
            return null;

        List<Client> clients = List.of();
        try {
            List<Client> tmp = new ArrayList<>();
            entityManager.createQuery(
                    "from Client client where client.session.id = :sessionId")
                    .setParameter("sessionId", sessionId)
                    .getResultList().forEach(result -> {
                        if (result instanceof Client)
                            tmp.add((Client) result);
            });
            clients = tmp;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return clients;
    }

    /**
     * Get the number of {@link Client} registered to the given {@link fr.utbm.jee_courses_management.entity.CourseSession}.
     *
     * @param sessionId The id of the {@link fr.utbm.jee_courses_management.entity.CourseSession}.
     * @return
     * <ul>
     *     <li>The number of registered {@link Client}.</li>
     *     <li>Will return null if the {@link fr.utbm.jee_courses_management.entity.CourseSession} does not exist.</li>
     * </ul>
     */
    public Integer getNumberOfRegisteredClientsToTheCourseSession(Integer sessionId) {
        List<Client> clients = getClientsBySessionId(sessionId);
        return clients != null ? clients.size() : null;
    }

    /**
     * Get a {@link Client} identified by his firstname, his lastname and
     * the id of the {@link fr.utbm.jee_courses_management.entity.CourseSession} for which he should be registered.
     *
     * @param firstname The firstname of the searched {@link Client}
     * @param lastname The lastname of the searched {@link Client}
     * @param sessionId The id of the {@link fr.utbm.jee_courses_management.entity.CourseSession} for which the {@link Client} should be registered.
     * @return The found {@link Client}.<br/>
     *          Null if the {@link Client} does not exist or if an error occurred.
     */
    public Client getClientByFirstnameAndLastnameAndSessionId(String firstname, String lastname, Integer sessionId) {
        Client result = null;
        try {
            result = (Client) entityManager.createQuery(
                        "from Client client where client.firstname = :firstname and client.lastname = :lastname and client.session.id = :sessionId")
                            .setParameter("firstname", firstname)
                            .setParameter("lastname", lastname)
                            .setParameter("sessionId", sessionId)
                            .getSingleResult();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return result;
    }

    /**
     * Register a {@link Client} to a {@link fr.utbm.jee_courses_management.entity.CourseSession}.
     *
     * @param client The {@link Client} to register.
     *               This object contains the id of the {@link fr.utbm.jee_courses_management.entity.CourseSession}
     *               on which the {@link Client} will be registered.
     * @return The registered {@link Client}.<br/>
     *          Null if the {@link Client} already exists or if an error occurred.
     */
    public Client register(Client client) {
        if (client.getId() != null)
            if (getClientById(client.getId()) != null)
                return null;
            else
                client.setId(null);

        Client result = null;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();

            result = getClientByFirstnameAndLastnameAndSessionId(
                    client.getFirstname(),
                    client.getLastname(),
                    client.getSession().getId());
        } catch (Exception exception) {
            exception.printStackTrace();

            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }

        return result;
    }
}
