package fr.utbm.jee_courses_management.util;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for the static methods in {@link Filter}
 *
 * @author macdrien
 */
@RunWith(JUnitPlatform.class)
@SelectPackages("fr.utbm.jee_courses_management")
public class FilterTest {

    /** Test method for {@link Filter#filterCourseTitle(Course, String)} */
    @Test
    public void testFilterCourseTitle() {
        DataSetup.dataSetup();

        // Get all sessions but pass into each filters
        final String KEYWORD = "5";

        CourseRepository repository = new CourseRepository();
        List<Course> courses = repository.getCourses(false);

        courses.forEach(course -> assertTrue(Filter.filterCourseTitle(course, KEYWORD)));
    }

    /** Test method for {@link Filter#filterCourseTitle(Course, String)} */
    @Test
    public void testFilterCourseTitleWithNotContainedKeyword() {
        DataSetup.dataSetup();

        // Get all sessions but pass into each filters
        final String KEYWORD = "Not contained";

        CourseRepository repository = new CourseRepository();
        List<Course> courses = repository.getCourses(false);

        courses.forEach(course -> assertFalse(Filter.filterCourseTitle(course, KEYWORD)));
    }
}
