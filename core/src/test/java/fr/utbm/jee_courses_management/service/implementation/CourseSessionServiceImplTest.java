package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.repository.CourseSessionRepository;
import fr.utbm.jee_courses_management.util.CourseSessionUtil;
import fr.utbm.jee_courses_management.util.CourseUtils;
import fr.utbm.jee_courses_management.util.Filter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/** Unit tests for {@link CourseSessionServiceImpl} methods */
class CourseSessionServiceImplTest {

    private static CourseSessionServiceImpl service;

    private static CourseSessionRepository repository;

    @BeforeAll
    public static void init() {
        repository = mock(CourseSessionRepository.class);
        service = new CourseSessionServiceImpl(repository);
    }

    /** Unit test for {@link CourseSessionServiceImpl#getCourseSession(Integer)} */
    @Test
    public void testGetCourseSession() {
        when(repository.getCourseSessionById(eq(CourseSessionUtil.ID))).thenReturn(CourseSessionUtil.COURSE_SESSION);
        assertEquals(CourseSessionUtil.COURSE_SESSION, service.getCourseSession(CourseSessionUtil.ID));
    }
}