package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.Client;
import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.repository.ClientRepository;
import fr.utbm.jee_courses_management.repository.CourseRepository;
import fr.utbm.jee_courses_management.repository.CourseSessionRepository;
import fr.utbm.jee_courses_management.service.CourseSessionService;
import fr.utbm.jee_courses_management.util.ClientUtils;
import fr.utbm.jee_courses_management.util.CourseSessionUtil;
import fr.utbm.jee_courses_management.util.CourseUtils;
import fr.utbm.jee_courses_management.util.Filter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/** Unit tests for {@link CourseServiceImpl} methods */
class CourseServiceImplTest {

    private static CourseServiceImpl service;

    private static CourseRepository repository;
    private static CourseSessionRepository courseSessionRepository;

    @BeforeAll
    public static void init() {
        repository = mock(CourseRepository.class);
        courseSessionRepository = mock(CourseSessionRepository.class);
        service = new CourseServiceImpl(repository, courseSessionRepository);
    }

    /** Unit test for {@link CourseServiceImpl#getCourses(Filter)} */
    @Test
    public void testGetCoursesWithNullFilter() {
        when(repository.getCourses(eq(true))).thenReturn(List.of(CourseUtils.COURSE));
        assertEquals(List.of(CourseUtils.COURSE), service.getCourses(null));
    }

    /** Unit test for {@link CourseServiceImpl#getCourses(Filter)} */
    @Test
    public void testGetCoursesWithNonNullFilter() {
        Course course = CourseUtils.COURSE;
        course.setSessions(List.of(CourseSessionUtil.COURSE_SESSION));
        List<Course> courses = List.of(course);

        when(repository.getCourses(eq(true))).thenReturn(courses);
        assertEquals(courses, service.getCourses(new Filter()));
    }
}