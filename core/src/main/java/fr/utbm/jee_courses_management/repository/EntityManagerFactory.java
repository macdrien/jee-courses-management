package fr.utbm.jee_courses_management.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/** A factory class which provide an unique {@link javax.persistence.EntityManager} */
public class EntityManagerFactory {

    /** The global {@link javax.persistence.EntityManagerFactory} */
    private static final javax.persistence.EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("courses-management");

    /** The unique instance of {@link EntityManager} to provide on requests */
    private static EntityManager entityManager = null;

    /**
     * Give the instance of {@link EntityManager}. If it does not exist yet, initialize it.
     *
     * @return The unique instance of {@link EntityManager}
     */
    // TODO Test
    public static EntityManager getEntityManager() {
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();

        return entityManager;
    }

}
