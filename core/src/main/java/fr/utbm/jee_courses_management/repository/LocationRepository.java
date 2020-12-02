package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Location;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("courses-management");
    EntityManager entityManager = null;

    /**
     * Get all locations from the entity {@link Location}
     *
     * @return A {List} containing all found locations. The returned List can be empty but it cannot be null.
     */
    public List<Location> getLocations() {
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();

        List<Location> locations = new ArrayList<>();
        for (Object result : entityManager.createQuery("from Location").getResultList())
            try {
                entityManager.detach(result);
                locations.add((Location) result);
            } catch (ClassCastException exception) {
                System.out.println("The object " + result.toString() + " cannot be casted");
            }

        return locations;
    }

    /**
     * Get one location from the entity {@link Location} from its city.
     *
     * @return A {Location} or null.
     */
    public Location getLocation(String city) {
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();

        Object result;

        try {
            result = entityManager.createQuery("from Location where city=:city")
                    .setParameter("city", city).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }

        try {
            entityManager.detach(result);
            return (Location) result;
        } catch (ClassCastException exception) {
            System.out.println("The object " + result.toString() + " cannot be casted");
        }

        return null;
    }

    /**
     * Get one location from the entity {@link Location} from its ID.
     *
     * @return A {Location} or null.
     */
    public Location getLocation(int id) {
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();

        Object result;

        try {
            result = entityManager.createQuery("from Location where id=:id")
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }


        try {
            entityManager.detach(result);
            return (Location) result;
        } catch (ClassCastException exception) {
            System.out.println("The object " + result.toString() + " cannot be casted");
        }

        return null;
    }

    /**
     * Save a new {@link Location} in database.
     *
     * @param location The {@link Location} to save.
     */
    public void save(Location location) {
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(location);
        entityManager.getTransaction().commit();
    }

}