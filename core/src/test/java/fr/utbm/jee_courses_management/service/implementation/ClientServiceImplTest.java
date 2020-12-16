package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.Client;
import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.repository.ClientRepository;
import fr.utbm.jee_courses_management.service.CourseSessionService;
import fr.utbm.jee_courses_management.util.ClientUtils;
import fr.utbm.jee_courses_management.util.CourseSessionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/** Unit tests for {@link ClientServiceImpl} methods */
class ClientServiceImplTest {

    private static ClientServiceImpl service;

    private static ClientRepository repository;
    private static CourseSessionService courseSessionService;

    @BeforeAll
    public static void init() {
        repository = mock(ClientRepository.class);
        courseSessionService = mock(CourseSessionService.class);
        service = new ClientServiceImpl(repository, courseSessionService);
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClient() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);

        when(courseSessionService.getCourseSession(eq(CourseSessionUtil.ID)))
                .thenReturn(CourseSessionUtil.COURSE_SESSION);
        when(repository.register(eq(client))).thenReturn(client);

        assertEquals(client, service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithNonExistingCourseSession() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);

        when(courseSessionService.getCourseSession(eq(CourseSessionUtil.ID))).thenReturn(null);

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithNullEmail() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setEmail(null);

        when(courseSessionService.getCourseSession(eq(CourseSessionUtil.ID)))
                .thenReturn(CourseSessionUtil.COURSE_SESSION);
        when(repository.register(eq(client))).thenReturn(client);

        assertEquals(client, service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithBlankEmail() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setEmail("");

        when(courseSessionService.getCourseSession(eq(CourseSessionUtil.ID)))
                .thenReturn(CourseSessionUtil.COURSE_SESSION);
        when(repository.register(eq(client))).thenReturn(client);

        assertEquals(client, service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithNullFirstname() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setFirstname(null);

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithBlankFirstname() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setFirstname("");

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithNullLastname() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setLastname(null);

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithBlankLastname() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setLastname("");

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithNullAddress() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setAddress(null);

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithBlankAddress() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setAddress("");

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithNullPhoneNumber() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setPhoneNumber(null);

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithBlankPhoneNumber() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setPhoneNumber("");

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithNullSession() {
        Client client = ClientUtils.CLIENT;
        client.setSession(CourseSessionUtil.COURSE_SESSION);
        client.setSession(null);

        assertNull(service.registerClient(client));
    }

    /** Unit test for {@link ClientServiceImpl#registerClient(Client)} */
    @Test
    void registerClientWithNullSessionId() {
        Client client = ClientUtils.CLIENT;
        CourseSession courseSession = CourseSessionUtil.COURSE_SESSION;
        courseSession.setId(null);
        client.setSession(courseSession);

        assertNull(service.registerClient(client));
    }
}