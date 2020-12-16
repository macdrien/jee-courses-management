package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.util.CourseSessionUtil;
import fr.utbm.jee_courses_management.util.CourseUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/** Unit tests for {@link CourseSessionRepository} methods */
public class CourseSessionRepositoryTest {

    /** The {@link CourseSessionRepository} to test */
    private static CourseSessionRepository repository;

    /** Private {@link CourseSessionRepository} fields which will be mocked */
    private static ClientRepository clientRepository;
    private static EntityManager entityManager;

    /** Objects which will be used in tests */
    private static final String REQUEST = "from CourseSession";
    /** Query will be used in {@link EntityManager} chained instructions */
    private static Query query;
    private static List<CourseSession> sessions;

    /** Initialize mocks and the repository to test */
    @BeforeAll
    static void init() {
        entityManager = mock(EntityManager.class);
        clientRepository = mock(ClientRepository.class);
        repository = new CourseSessionRepository(entityManager, clientRepository);

        CourseSession session1 = new CourseSession(1, LocalDate.MIN.plusDays(1), LocalDate.MAX.minusDays(1), 50, null, null, null, 5),
                        session2 = new CourseSession(2, LocalDate.MIN.plusDays(1), LocalDate.MAX.minusDays(1), 50, null, null, null, 5),
                        session3 = new CourseSession(3, LocalDate.MIN.plusDays(1), LocalDate.MAX.minusDays(1), 50, null, null, null, 5),
                        session4 = new CourseSession(4, LocalDate.MIN.plusDays(1), LocalDate.MAX.minusDays(1), 50, null, null, null, 5);
        sessions = List.of(session1, session2, session3, session4);
    }

    @BeforeEach
    void setup() {
        query = mock(Query.class);
    }

    /** Unit test for {@link CourseSessionRepository#getCourseSessions()} */
    @Test
    public void testGetCourseSessions() {
        when(entityManager.createQuery(eq(REQUEST))).thenReturn(query);
        when(query.getResultList()).thenReturn(sessions);
        when(clientRepository.getNumberOfRegisteredClientsToTheCourseSession(any())).thenReturn(5);

        assertEquals(sessions, repository.getCourseSessions());
    }

    /** Unit test for {@link CourseSessionRepository#getCourseSessionsByCourseId(java.lang.Integer)} */
    @Test
    public void testGetCourseSessionsByCourseId() {
        when(entityManager.createQuery(
                eq(REQUEST + " session where session.course.id = :courseId")))
                .thenReturn(query);
        when(query.setParameter(eq("courseId"), eq(CourseUtils.ID))).thenReturn(query);
        when(query.getResultList()).thenReturn(sessions);
        when(clientRepository.getNumberOfRegisteredClientsToTheCourseSession(eq(CourseSessionUtil.ID))).thenReturn(5);

        assertEquals(sessions, repository.getCourseSessionsByCourseId(CourseUtils.ID));
    }

    /** Unit test for {@link CourseSessionRepository#getCourseSessionById(Integer)} */
    @Test
    public void testGetCourseSessionByIdWithExistingCourseSession() {
        when(entityManager.createQuery(eq(REQUEST + " session where session.id = :id"))).thenReturn(query);
        when(query.setParameter(eq("id"), eq(CourseSessionUtil.ID))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(CourseSessionUtil.COURSE_SESSION);
        when(clientRepository.getNumberOfRegisteredClientsToTheCourseSession(eq(CourseSessionUtil.ID))).thenReturn(5);

        assertEquals(CourseSessionUtil.COURSE_SESSION, repository.getCourseSessionById(CourseSessionUtil.ID));
    }

    /** Unit test for {@link CourseSessionRepository#getCourseSessionById(Integer)} */
    @Test
    public void testGetCourseSessionByIdWithNonExistingCourseSession() {
        when(entityManager.createQuery(eq(REQUEST + " session where session.id = :id"))).thenReturn(query);
        when(query.setParameter(eq("id"), eq(CourseSessionUtil.ID))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(NoResultException.class);

        assertNull(repository.getCourseSessionById(CourseSessionUtil.ID));
    }

    /** Unit test for {@link CourseSessionRepository#doesCourseSessionExists(Integer)} */
    @Test
    public void testDoesCourseSessionExists() {
        when(entityManager.createQuery(
                eq("select session.id from CourseSession session where session.id = :id")))
                .thenReturn(query);
        when(query.setParameter(eq("id"), eq(CourseSessionUtil.ID))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(CourseSessionUtil.ID);

        assertTrue(repository.doesCourseSessionExists(CourseSessionUtil.ID));
    }

    /** Unit test for {@link CourseSessionRepository#doesCourseSessionExists(Integer)} */
    @Test
    public void testDoesCourseSessionExistsWithNonExistingSession() {
        when(entityManager.createQuery(
                eq("select session.id from CourseSession session where session.id = :id")))
                .thenReturn(query);
        when(query.setParameter(eq("id"), eq(CourseSessionUtil.ID))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(NoResultException.class);

        assertFalse(repository.doesCourseSessionExists(CourseSessionUtil.ID));
    }
}
