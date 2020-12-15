package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@RunWith(JUnitPlatform.class)
@SelectPackages("fr.utbm.jee_courses_management")
public class CourseRepositoryTest {

    @Test
    public void testGetCourses() {
        CourseRepository repository = new CourseRepository();

        Course testCourse1 = new Course(1, "LO54", null);
        Course testCourse2 = new Course(2, "AD50", null);
        Course testCourse3 = new Course(3, "SR50", null);

        List<Course> courses = List.of(testCourse1, testCourse2, testCourse3);

        assertEquals(courses, repository.getCourses(false));
    }
}
