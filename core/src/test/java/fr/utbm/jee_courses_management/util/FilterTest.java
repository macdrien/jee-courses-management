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

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithNullCoursesList() {
        assertNull(Filter.filterCoursesAndSessions(null, null));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithEmptyCoursesList() {
        assertEquals(List.of(), Filter.filterCoursesAndSessions(List.of(), null));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithNullFilter() {
        List<Course> courses = courseRepository.getCourses(false);
        assertThrows(NullPointerException.class, () -> {Filter.filterCoursesAndSessions(courses, null); });
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithoutFilteringOptions() {
        Filter filter = new Filter();
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(courses.subList(0, courses.size() - 1), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithBlankKeywordOptionAndNoOtherOptions() {
        Filter filter = new Filter();
        filter.setKeyword("");
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(courses.subList(0, courses.size() - 1), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithKeywordTakingAllCoursesAndNoOtherOptions() {
        Filter filter = new Filter();
        // In the test data, "5" is in all course title
        filter.setKeyword("5");
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(courses.subList(0, courses.size() - 1), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithKeywordTakingNoCoursesAndNoOtherOptions() {
        Filter filter = new Filter();
        filter.setKeyword("Not contained in any course");
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(List.of(), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithKeywordTakingSomeCoursesAndNoOtherOptions() {
        Filter filter = new Filter();
        /*
         * Courses titles are "LO54", "AD50" and "SR50".
         * Index:              0    ,  1     and  2
         *
         * The keyword "50" will exclude "LO54" and keep the two others.
         */
        filter.setKeyword("50");
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(List.of(courses.get(1)), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithNonNullStartingDateTakingAllCoursesAndNoOtherOptions() {
        Filter filter = new Filter();
        filter.setStartingDate(LocalDate.MIN);
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(courses.subList(0, courses.size() - 1), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    // TODO fix it by removing all courses which do not have any session
    @Test
    public void testFilterCoursesAndSessionsWithNonNullStartingDateTakingNoCoursesAndNoOtherOptions() {
        Filter filter = new Filter();
        filter.setStartingDate(LocalDate.MAX);
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(List.of(), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithNonNullStartingDateTakingSomeCoursesAndNoOtherOptions() {
        LocalDate startingDate = LocalDate.of(2020, 6, 1);
        Filter filter = new Filter();
        // That will exclude which begins in the 2020's first semester or before
        filter.setStartingDate(startingDate);

       List<Course> courses = courseRepository.getCourses(true);

        for (Course course: Filter.filterCoursesAndSessions(courses, filter))
            course.getSessions().forEach(session -> {
                    if (0 < startingDate.compareTo(session.getStartingDate()))
                        fail("The session " + session.getId() + " which starts on " + session.getStartingDate() +  " must be excluded but it is not");
            });
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithNonNullEndingDateTakingAllCoursesAndNoOtherOptions() {
        Filter filter = new Filter();
        filter.setEndingDate(LocalDate.MAX);
        List<Course> courses = courseRepository.getCourses(true);

        // Do not expect the last course because it has no sessions -> It will be filtered
        assertEquals(courses.subList(0, courses.size() - 1), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    // TODO Fix with more research
    @Test
    public void testFilterCoursesAndSessionsWithNonNullEndingDateTakingNoCoursesAndNoOtherOptions() {
        Filter filter = new Filter();
        filter.setEndingDate(LocalDate.MIN);
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(List.of(), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithNonNullEndingDateTakingSomeCoursesAndNoOtherOptions() {
        LocalDate endingDate = LocalDate.of(2021, 1, 1);
        Filter filter = new Filter();
        // That will exclude which begins in the 2020's first semester or before
        filter.setEndingDate(endingDate);

        List<Course> courses = courseRepository.getCourses(true);

        for (Course course: Filter.filterCoursesAndSessions(courses, filter))
            course.getSessions().forEach(session -> {
                if (endingDate.compareTo(session.getStartingDate()) < 0)
                    fail("The session " + session.getId() + " which finishes on " + session.getEndingDate() +  " must be excluded but it is not");
            });
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithBlankCityAndNoOtherOption() {
        Filter filter = new Filter();
        filter.setCity("");
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(courses.subList(0, courses.size() - 1), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithCityTakingAllSessionsAndNoOtherOption() {
        Filter filter = new Filter();
        // Available cities are "Mulhouse", "Belfort" and "Montbeliard"
        filter.setCity("e");
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(courses, Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    // cf comment on {@link FilterTest#testFilterCoursesAndSessionsWithNonNullStartingDateTakingNoCoursesAndNoOtherOptions()}
    @Test
    public void testFilterCoursesAndSessionsWithCityTakingNoSessionsAndNoOtherOption() {
        Filter filter = new Filter();
        // Available cities are "Mulhouse", "Belfort" and "Montbeliard"
        filter.setCity("z");
        List<Course> courses = courseRepository.getCourses(true);

        assertEquals(List.of(), Filter.filterCoursesAndSessions(courses, filter));
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWithCityTakingSomeSessionsAndNoOtherOption() {
        final String SEARCHED_NAME = "M";
        Filter filter = new Filter();
        // Available cities are "Mulhouse", "Belfort" and "Montbeliard"
        filter.setCity(SEARCHED_NAME);
        List<Course> courses = courseRepository.getCourses(true);

        Filter.filterCoursesAndSessions(courses, filter).forEach(course -> {
            course.getSessions().forEach(session -> {
                if (!session.getLocation().getCity().contains(SEARCHED_NAME))
                    fail("The session " + session.getId() + " taking place in/at " + session.getLocation().getCity() + " must be excluded but it is not");
            });
        });
    }

    /** Test method for {@link Filter#filterCoursesAndSessions(List, Filter)} */
    @Test
    public void testFilterCoursesAndSessionsWhichAreIn2020Only() {
        final LocalDate BEGINNING_OF_2020 = LocalDate.of(2020, 1, 1),
                ENDING_OF_2020 = LocalDate.of(2020, 12, 31);
        Filter filter = new Filter(null, BEGINNING_OF_2020,
                                    ENDING_OF_2020, null);
        List<Course> courses = courseRepository.getCourses(true);

        Filter.filterCoursesAndSessions(courses, filter).forEach(course -> {
            course.getSessions().forEach(session -> {
                if (session.getStartingDate().isBefore(BEGINNING_OF_2020))
                    fail("The session " + session.getId() + " beginning the " + session.getStartingDate() + " must be excluded but it is not");

                if (session.getEndingDate().isAfter(ENDING_OF_2020))
                    fail("The session " + session.getId() + " ending the " + session.getEndingDate() + " must be excluded but it is not");
            });
        });
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
