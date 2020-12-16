package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.util.CourseSessionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

/** Unit tests for {@link CourseRepository} methods */
public class CourseRepositoryTest {

    /** The {@link CourseRepository} to test */
    private static CourseRepository repository;

    /** Private {@link CourseRepository} fields which will be mocked */
    private static CourseSessionRepository courseSessionRepository;
    private static EntityManager entityManager;

    /** Objects which will be used in tests */
    private static final String REQUEST = "from Course";
    private static List<Course> COURSES;
    /**
     * Query will be used in the chained instruction in {@link CourseRepository#getCourses(boolean)}
     * to get the result list of the request
     */
    private static Query query;

    /** Initialize mocks and the repository to test */
    @BeforeAll
    static void init() {
        entityManager = mock(EntityManager.class);
        courseSessionRepository = mock(CourseSessionRepository.class);
        repository = new CourseRepository(entityManager, courseSessionRepository);

        query = mock(Query.class);

        Course testCourse1 = new Course(1, "LO54", null),
                testCourse2 = new Course(2, "AD50", null),
                testCourse3 = new Course(3, "SR50", null);
        COURSES = List.of(testCourse1, testCourse2, testCourse3);
    }

    /** Unit test for {@link CourseRepository#getCourses(boolean)} */
    @Test
    public void testGetCoursesWithoutSessions() {
        // Mock the entityManager request
        when(entityManager.createQuery(eq(REQUEST))).thenReturn(query);
        when(query.getResultList()).thenReturn(COURSES);

        assertEquals(COURSES, repository.getCourses(false));

        // Because loadSessions is false
        verify(courseSessionRepository, never()).getCourseSessionsByCourseId(any());
    }

    /** Unit test for {@link CourseRepository#getCourses(boolean)} */
    @Test
    public void testGetCoursesWithSessions() {
        // Mock the entityManager request
        when(entityManager.createQuery(eq(REQUEST))).thenReturn(query);
        when(query.getResultList()).thenReturn(COURSES);

        when(courseSessionRepository.getCourseSessionsByCourseId(any()))
                .thenReturn(List.of(CourseSessionUtil.COURSE_SESSION));

        assertEquals(COURSES, repository.getCourses(true));

        // Because loadSessions is true -> Called once per course
        verify(courseSessionRepository, times(COURSES.size())).getCourseSessionsByCourseId(any());
    }
}
