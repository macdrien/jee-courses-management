package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Course;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@RunWith(JUnitPlatform.class)
@SelectPackages("fr.utbm.jee_courses_management")
public class CourseRepositoryTest {

    @Test
    public void testGetCourses() {
        CourseRepository repository = new CourseRepository();

        final int TEST_COURSE_ID_1 = 1;
        final String TEST_COURSE_TITLE_1 = "title";
        final int TEST_COURSE_ID_2 = 2;
        final String TEST_COURSE_TITLE_2 = "second title";

        Course testCourse1 = new Course();
        testCourse1.setId(TEST_COURSE_ID_1);
        testCourse1.setTitle(TEST_COURSE_TITLE_1);
        repository.save(testCourse1);
        Course testCourse2 = new Course();
        testCourse2.setId(TEST_COURSE_ID_2);
        testCourse2.setTitle(TEST_COURSE_TITLE_2);
        repository.save(testCourse2);

        List<Course> courses = List.of(testCourse1, testCourse2);

        assertEquals(courses, repository.getCourses());
    }
}
