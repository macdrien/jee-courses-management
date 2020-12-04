package fr.utbm.jee_courses_management.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

    private static javax.persistence.EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("courses-management");
    private static EntityManager entityManager = null;

    public static EntityManager getEntityManager() {
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();

        return entityManager;
    }

}
