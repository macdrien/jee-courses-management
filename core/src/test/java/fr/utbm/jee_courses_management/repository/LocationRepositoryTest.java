package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Location;
import fr.utbm.jee_courses_management.util.LocationUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/** Unit tests for {@link LocationRepository} methods */
public class LocationRepositoryTest {

    /** The {@link LocationRepository} to test */
    private static LocationRepository repository;

    /** Private {@link LocationRepository} fields which will be mocked */
    private static EntityManager entityManager;

    /** Objects which will be used in tests */
    private static final String REQUEST = "from Location";
    private static List<Location> locations;
    /**
     * Query will be used in the chained instruction in {@link CourseRepository#getCourses(boolean)}
     * to get the result list of the request
     */
    private static Query query;

    /** Initialize mocks and the repository to test */
    @BeforeAll
    static void init() {
        entityManager = mock(EntityManager.class);
        repository = new LocationRepository(entityManager);

        Location location1 = LocationUtils.LOCATION,
                location2 = new Location(2, "Mulhouse"),
                location3 = new Location(3, "Montbéliard"),
                location4 = new Location(4, "Toulouse");
        locations = List.of(location1, location2, location3, location4);
    }

    @BeforeEach
    void setup() {
        query = mock(Query.class);
    }

    /** Unit test for {@link LocationRepository#getLocations()} */
    @Test
    public void testGetLocations() {
        when(entityManager.createQuery(eq(REQUEST))).thenReturn(query);
        when(query.getResultList()).thenReturn(locations);

        assertEquals(locations, repository.getLocations());
    }

    /** Unit test for {@link LocationRepository#getLocation(String)} */
    @Test
    public void testGetLocationWithExistingLocation() {
        when(entityManager.createQuery(eq(REQUEST + " where city=:city"))).thenReturn(query);
        when(query.setParameter(eq("city"), eq(LocationUtils.CITY))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(LocationUtils.LOCATION);

        assertEquals(LocationUtils.LOCATION, repository.getLocation(LocationUtils.CITY));
    }

    /** Unit test for {@link LocationRepository#getLocation(String)} */
    @Test
    public void testGetLocationWithNonExistingLocation() {
        when(entityManager.createQuery(eq(REQUEST + " where city=:city"))).thenReturn(query);
        when(query.setParameter(eq("city"), eq(LocationUtils.CITY))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(NoResultException.class);

        assertNull(repository.getLocation(LocationUtils.CITY));
    }

    /** Unit test for {@link LocationRepository#getLocation(int)} */
    @Test
    public void testGetLocationIntegerWithExistingLocation() {
        when(entityManager.createQuery(eq(REQUEST + " where id=:id"))).thenReturn(query);
        when(query.setParameter(eq("id"), eq(LocationUtils.ID))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(LocationUtils.LOCATION);

        assertEquals(LocationUtils.LOCATION, repository.getLocation(LocationUtils.ID));
    }

    /** Unit test for {@link LocationRepository#getLocation(int)} */
    @Test
    public void testGetLocationIntegerWithNonExistingLocation() {
        when(entityManager.createQuery(eq(REQUEST + " where id=:id"))).thenReturn(query);
        when(query.setParameter(eq("id"), eq(LocationUtils.ID))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(NoResultException.class);

        assertNull(repository.getLocation(LocationUtils.ID));
    }

    /** Unit test for {@link LocationRepository#save(Location)} */
    @Test
    public void testSave() {
        EntityTransaction entityTransaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(entityTransaction);
        when(entityManager.merge(eq(LocationUtils.LOCATION))).thenReturn(LocationUtils.LOCATION);

        repository.save(LocationUtils.LOCATION);
    }
}
