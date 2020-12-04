package fr.utbm.jee_courses_management.util;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.repository.CourseRepository;
import fr.utbm.jee_courses_management.repository.CourseSessionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unit tests for the static methods in {@link Filter}
 *
 * @author macdrien
 */
@RunWith(JUnitPlatform.class)
@SelectPackages("fr.utbm.jee_courses_management")
public class FilterTest {

    private static CourseRepository courseRepository;

    private static CourseSessionRepository courseSessionRepository;

    @BeforeAll
    public static void prepareData() {
        DataSetup.dataSetup();
        courseRepository = new CourseRepository();
        courseSessionRepository = new CourseSessionRepository();
    }

    /** Test method for {@link Filter#filterCourseTitle(Course, String)} */
    @Test
    public void testFilterCourseTitle() {
        // Get all sessions but pass into each filters
        final String KEYWORD = "5";

        List<Course> courses = courseRepository.getCourses(false);

        courses.forEach(course -> assertTrue(Filter.filterCourseTitle(course, KEYWORD)));
    }

    /** Test method for {@link Filter#filterCourseTitle(Course, String)} */
    @Test
    public void testFilterCourseTitleWithNotContainedKeyword() {
        // Get all sessions but pass into each filters
        final String KEYWORD = "Not contained";

        List<Course> courses = courseRepository.getCourses(false);

        courses.forEach(course -> assertFalse(Filter.filterCourseTitle(course, KEYWORD)));
    }

    /** Test method for {@link Filter#filterSessionBeginAfter(CourseSession, LocalDate)} */
    @Test
    public void testFilterSessionBeginAfterWithOnlyAfterBeginningCondition() {
        List<CourseSession> sessions = courseSessionRepository.getCourseSessions();

        sessions.forEach(session -> assertTrue(Filter.filterSessionBeginAfter(session, LocalDate.MIN)));
    }

    /** Test method for {@link Filter#filterSessionBeginAfter(CourseSession, LocalDate)} */
    @Test
    public void testFilterSessionBeginAfterWithOnlyOnDateBeginningCondition() {
        List<CourseSession> sessions = courseSessionRepository.getCourseSessions();

        sessions.forEach(session -> assertTrue(Filter.filterSessionBeginAfter(session, session.getStartingDate())));
    }

    /** Test method for {@link Filter#filterSessionBeginAfter(CourseSession, LocalDate)} */
    @Test
    public void testFilterSessionBeginAfterWithOnlyErrors() {
        List<CourseSession> sessions = courseSessionRepository.getCourseSessions();

        sessions.forEach(session -> assertFalse(Filter.filterSessionBeginAfter(session, LocalDate.MAX)));
    }

    /** Test method for {@link Filter#filterSessionFinishBefore(CourseSession, LocalDate)} */
    @Test
    public void testFilterSessionFinishBeforeWithOnlyBeforeEndingCondition() {;
        List<CourseSession> sessions = courseSessionRepository.getCourseSessions();

        sessions.forEach(session -> assertTrue(Filter.filterSessionFinishBefore(session, LocalDate.MAX)));
    }

    /** Test method for {@link Filter#filterSessionFinishBefore(CourseSession, LocalDate)} */
    @Test
    public void testFilterSessionFinishBeforeWithOnlyOnDateEndingCondition() {
        CourseSessionRepository repository = new CourseSessionRepository();
        List<CourseSession> sessions = repository.getCourseSessions();

        sessions.forEach(session -> assertTrue(Filter.filterSessionFinishBefore(session, session.getEndingDate())));
    }

    /** Test method for {@link Filter#filterSessionFinishBefore(CourseSession, LocalDate)} */
    @Test
    public void testFilterSessionFinishBeforeWithOnlyToLateEndingCondition() {
        List<CourseSession> sessions = courseSessionRepository.getCourseSessions();

        sessions.forEach(session -> assertFalse(Filter.filterSessionFinishBefore(session, LocalDate.MIN)));
    }

    /** Test method for {@link Filter#filterSessionInCity(CourseSession, String)} */
    @Test
    public void testFilterSessionInCity() {
        List<CourseSession> sessions = courseSessionRepository.getCourseSessions();
        List<CourseSession> expected = sessions.subList(0, 2);

        sessions = sessions.stream()
                .filter(session -> Filter.filterSessionInCity(session, "Belfort"))
                .collect(Collectors.toList());

        assertEquals(expected, sessions);
    }

    /** Test method for {@link Filter#filterSessionInCity(CourseSession, String)} */
    @Test
    public void testFilterSessionInCityWithoutCorresponding() {
        List<CourseSession> sessions = courseSessionRepository.getCourseSessions();

        sessions = sessions.stream()
                .filter(session -> Filter.filterSessionInCity(session, "Non existing city"))
                .collect(Collectors.toList());

        assertEquals(List.of(), sessions);
    }
}
