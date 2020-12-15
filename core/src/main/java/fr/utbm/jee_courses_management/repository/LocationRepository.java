package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.entity.Location;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/** Class containing methods accessing to the database on the {@link CourseSession} entity. */
public class LocationRepository {

    /** {@link EntityManager} to allow requests and operations. */
    EntityManager entityManager = null;

    /**
     * Get all locations from the entity {@link Location}
     *
     * @return A {@link List} containing all found {@link Location}s. The returned {@link List} can be empty but it cannot be null.
     */
    // TODO Test
    public List<Location> getLocations() {
        if (entityManager == null)
            entityManager = EntityManagerFactory.getEntityManager();

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
     * Get one {@link Location} from the entity {@link Location} from its city.
     *
     * @return A {@link Location} or null.
     */
    // TODO Test
    public Location getLocation(String city) {
        if (entityManager == null)
            entityManager = EntityManagerFactory.getEntityManager();

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
     * Get one {@link Location} from the entity {@link Location} from its ID.
     *
     * @return A {@link Location} or null.
     */
    // TODO Test
    public Location getLocation(int id) {
        if (entityManager == null)
            entityManager = EntityManagerFactory.getEntityManager();

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
    // TODO Test
    public void save(Location location) {
        if (entityManager == null)
            entityManager = EntityManagerFactory.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(location);
        entityManager.getTransaction().commit();
    }

}