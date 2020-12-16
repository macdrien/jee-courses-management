package fr.utbm.jee_courses_management.controller;

import fr.utbm.jee_courses_management.service.CourseSessionService;
import fr.utbm.jee_courses_management.util.CourseSessionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/** Unit tests for {@link CourseSessionController} */
public class CourseSessionControllerTest {

    /** The {@link CourseSessionController} to test */
    private static CourseSessionController controller;

    /** A mock of {@link CourseSessionService} used by controller */
    private static CourseSessionService service;

    /** Initialize controller and service */
    @BeforeAll
    static void init() {
        service = Mockito.mock(CourseSessionService.class);
        controller = new CourseSessionController(service);
    }

    /** Unit test of {@link CourseSessionController#CourseSessionController(CourseSessionService)} */
    @Test
    public void testGetCourseSessionById() {
        when(service.getCourseSession(eq(CourseSessionUtil.ID))).thenReturn(CourseSessionUtil.COURSE_SESSION);
        assertEquals(CourseSessionUtil.COURSE_SESSION, controller.getCourseSessionById(CourseSessionUtil.ID));
    }
}
