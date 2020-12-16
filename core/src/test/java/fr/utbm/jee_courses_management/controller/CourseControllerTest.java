package fr.utbm.jee_courses_management.controller;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.service.CourseService;
import fr.utbm.jee_courses_management.util.CourseUtils;
import fr.utbm.jee_courses_management.util.Filter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/** Unit tests for {@link CourseController} */
public class CourseControllerTest {

    /** The {@link CourseController} to test */
    private static CourseController controller;

    /** A mock of {@link fr.utbm.jee_courses_management.service.CourseService} used by controller */
    private static CourseService service;

    /** Initialize controller and service */
    @BeforeAll
    static void init() {
        service = Mockito.mock(CourseService.class);
        controller = new CourseController(service);
    }

    /** Unit test of {@link CourseController#getCourses(Filter)} */
    @Test
    public void testGetCourses() {
        List<Course> courses = List.of(CourseUtils.COURSE);
        when(service.getCourses(any())).thenReturn(courses);
        assertEquals(courses, controller.getCourses(null));
    }
}
