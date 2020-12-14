package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Client;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Class containing methods accessing to the database on the {@link Client} entity.
 */
public class ClientRepository implements Serializable {

    /** {@link EntityManager} to allow requests and operations. */
    private EntityManager entityManager;

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
    // TODO Test
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
     * Get a {@link Client} identified by his firstname, his lastname and
     * the id of the {@link fr.utbm.jee_courses_management.entity.CourseSession} for which he should be registered.
     *
     * @param firstname The firstname of the searched {@link Client}
     * @param lastname The lastname of the searched {@link Client}
     * @param sessionId The id of the {@link fr.utbm.jee_courses_management.entity.CourseSession} for which the {@link Client} should be registered.
     * @return The found {@link Client}.<br/>
     *          Null if the {@link Client} does not exist or if an error occurred.
     */
    // TODO Test
    public Client getClientByFirstnameAndLastnameAndSessionId(String firstname, String lastname, Integer sessionId) {
        Client result = null;
        try {
            result = (Client) entityManager.createQuery(
                        "from Client client" +
                                "where client.firstname = :firstname and " +
                                "client.lastname = :lastname and " +
                                ".session.id = :sessionId")
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
    // TODO Test
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
