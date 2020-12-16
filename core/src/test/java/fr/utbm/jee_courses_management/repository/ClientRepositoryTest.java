package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Client;
import fr.utbm.jee_courses_management.util.ClientUtils;
import fr.utbm.jee_courses_management.util.CourseSessionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/** Unit tests for {@link ClientRepository} methods */
public class ClientRepositoryTest {

    /**
     * The {@link ClientRepository} to test
     */
    private static ClientRepository repository;

    /**
     * Private {@link ClientRepository} fields which will be mocked
     */
    private static CourseSessionRepository courseSessionRepository;
    private static EntityManager entityManager;

    /**
     * Objects which will be used in tests
     */
    private static final String REQUEST = "from Client";
    private static List<Client> CLIENTS;
    /**
     * Query will be used in the chained instruction in {@link CourseRepository#getCourses(boolean)}
     * to get the result list of the request
     */
    private static Query query;

    /**
     * Initialize mocks and the repository to test
     */
    @BeforeAll
    static void init() {
        entityManager = mock(EntityManager.class);
        courseSessionRepository = mock(CourseSessionRepository.class);
        repository = new ClientRepository(entityManager, courseSessionRepository);

        Client client1 = ClientUtils.CLIENT,
                client2 = ClientUtils.CLIENT,
                client3 = ClientUtils.CLIENT;
        client2.setId(2);
        client3.setId(3);
        CLIENTS = List.of(client1, client2, client3);
    }

    /**
     * Initialize a new {@link Query} mock before each new test
     */
    @BeforeEach
    void setup() {
        query = mock(Query.class);
    }

    /**
     * Unit test for {@link ClientRepository#getClientById(Integer)}
     */
    @Test
    public void testGetClientByIdWithExistingClient() {
        when(entityManager.createQuery(eq(REQUEST + " client where client.id = :id"))).thenReturn(query);
        when(query.setParameter(eq("id"), eq(ClientUtils.ID))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(ClientUtils.CLIENT);

        assertEquals(ClientUtils.CLIENT, repository.getClientById(ClientUtils.ID));
    }

    /**
     * Unit test for {@link ClientRepository#getClientById(Integer)}
     */
    @Test
    public void testGetClientByIdWithNonExistingClient() {
        when(entityManager.createQuery(eq(REQUEST + " client where client.id = :id"))).thenReturn(query);
        when(query.setParameter(eq("id"), eq(ClientUtils.ID))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(NoResultException.class);

        assertNull(repository.getClientById(ClientUtils.ID));
    }

    /** Unit test for {@link ClientRepository#getClientsBySessionId(Integer)} */
    @Test
    public void testGetClientsBySessionIdWithExistingCourseSessionWithRegisteredClients() {
        when(entityManager.createQuery(eq(REQUEST + " client where client.session.id = :sessionId")))
                .thenReturn(query);
        when(query.setParameter(eq("sessionId"), eq(CourseSessionUtil.ID))).thenReturn(query);
        when(query.getResultList()).thenReturn(CLIENTS);

        assertEquals(CLIENTS, repository.getClientsBySessionId(CourseSessionUtil.ID));
    }

    /** Unit test for {@link ClientRepository#getClientsBySessionId(Integer)} */
    @Test
    public void testGetClientsBySessionIdWithExistingCourseSessionWithoutRegisteredClients() {
        when(entityManager.createQuery(eq(REQUEST + " client where client.session.id = :sessionId")))
                .thenReturn(query);
        when(query.setParameter(eq("sessionId"), eq(CourseSessionUtil.ID))).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of());

        assertEquals(List.of(), repository.getClientsBySessionId(CourseSessionUtil.ID));
    }

    /** Unit test for {@link ClientRepository#getClientsBySessionId(Integer)} */
    @Test
    public void testGetClientsBySessionIdWithNonExistingCourseSession() {
        assertNull(repository.getClientsBySessionId(-1));
    }

    /** Unit test for {@link ClientRepository#getNumberOfRegisteredClientsToTheCourseSession(Integer)} */
    @Test
    public void testGetNumberOfRegisteredClientsToTheCourseSessionWithExistingSession() {
        ClientRepository spiedRepository = spy(repository);

        doReturn(CLIENTS).when(spiedRepository).getClientsBySessionId(eq(CourseSessionUtil.ID));

        // Integer parsing to avoid ambiguity between assertEquals(int, int) and assertEquals(Object, Object)
        assertEquals(Integer.valueOf(CLIENTS.size()),
                spiedRepository.getNumberOfRegisteredClientsToTheCourseSession(CourseSessionUtil.ID));
    }

    /** Unit test for {@link ClientRepository#getNumberOfRegisteredClientsToTheCourseSession(Integer)} */
    @Test
    public void testGetNumberOfRegisteredClientsToTheCourseSessionWithNonExistingSession() {
        ClientRepository spiedRepository = spy(repository);

        doReturn(null).when(spiedRepository).getClientsBySessionId(eq(CourseSessionUtil.ID));

        assertNull(spiedRepository.getNumberOfRegisteredClientsToTheCourseSession(CourseSessionUtil.ID));
    }

    /** Unit test for {@link ClientRepository#getClientByFirstnameAndLastnameAndSessionId(String, String, Integer)} */
    @Test
    public void testGetClientByFirstnameAndLastnameAndSessionIdWithExistingClient() {
        when(entityManager.createQuery(
                eq(REQUEST + " client where client.firstname = :firstname and client.lastname = :lastname and client.session.id = :sessionId")))
                .thenReturn(query);
        when(query.setParameter((String) any(), any())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(ClientUtils.CLIENT);

        assertEquals(ClientUtils.CLIENT,
                repository.getClientByFirstnameAndLastnameAndSessionId(ClientUtils.FIRSTNAME, ClientUtils.LASTNAME, CourseSessionUtil.ID));
    }

    /** Unit test for {@link ClientRepository#getClientByFirstnameAndLastnameAndSessionId(String, String, Integer)} */
    @Test
    public void testGetClientByFirstnameAndLastnameAndSessionIdWithNonExistingClient() {
        when(entityManager.createQuery(
                eq(REQUEST + " client where client.firstname = :firstname and client.lastname = :lastname and client.session.id = :sessionId")))
                .thenReturn(query);
        when(query.setParameter((String) any(), any())).thenReturn(query);
        when(query.getResultList()).thenThrow(NoResultException.class);

        assertNull(repository.getClientByFirstnameAndLastnameAndSessionId(ClientUtils.FIRSTNAME, ClientUtils.LASTNAME, CourseSessionUtil.ID));
    }

    /** Unit test for {@link ClientRepository#register(Client)} */
    @Test
    public void testRegisterWithNonExistingClientWithoutId() {
        ClientRepository spiedRepository = spy(repository);

        Client toRegister = ClientUtils.CLIENT;
        toRegister.setId(null);

        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);

        when(entityManager.getTransaction()).thenReturn(mock(EntityTransaction.class));
        doReturn(ClientUtils.CLIENT)
                .when(spiedRepository).getClientByFirstnameAndLastnameAndSessionId(
                        eq(ClientUtils.FIRSTNAME), eq(ClientUtils.LASTNAME), eq(CourseSessionUtil.ID));

        assertEquals(client, spiedRepository.register(toRegister));
    }

    /** Unit test for {@link ClientRepository#register(Client)} */
    @Test
    public void testRegisterWithNonExistingClientWithId() {
        ClientRepository spiedRepository = spy(repository);

        Client toRegister = ClientUtils.CLIENT;

        doReturn(null).when(spiedRepository).getClientById(eq(ClientUtils.ID));
        when(entityManager.getTransaction()).thenReturn(mock(EntityTransaction.class));
        doReturn(ClientUtils.CLIENT)
                .when(spiedRepository).getClientByFirstnameAndLastnameAndSessionId(
                eq(ClientUtils.FIRSTNAME), eq(ClientUtils.LASTNAME), eq(CourseSessionUtil.ID));

        assertEquals(ClientUtils.CLIENT, spiedRepository.register(toRegister));
    }

    /** Unit test for {@link ClientRepository#register(Client)} */
    @Test
    public void testRegisterWithExistingClientWithId() {
        ClientRepository spiedRepository = spy(repository);
        Client toRegister = ClientUtils.CLIENT;
        EntityTransaction entityTransaction = mock(EntityTransaction.class);

        doReturn(ClientUtils.CLIENT).when(spiedRepository).getClientById(eq(ClientUtils.ID));
        when(entityManager.getTransaction()).thenReturn(entityTransaction);
        doThrow(EntityExistsException.class).when(entityManager).persist(eq(toRegister));
        when(entityTransaction.isActive()).thenReturn(true);

        assertNull(spiedRepository.register(toRegister));

        verify(entityTransaction).rollback();
    }
}
