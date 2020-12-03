package fr.utbm.jee_courses_management.util;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.entity.CourseSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter implements Serializable {

    /** A keyword which must be in the {@link fr.utbm.jee_courses_management.entity.Course} title */
    private String keyword;

    /** The minimum date to begin the session */
    private LocalDate startingDate;

    /** The minimum date to terminate the session */
    private LocalDate endingDate;

    /** The name of the city where the session have to take place */
    private String city;

    /**
     * Filter a {@link List} of {@link Course} and their {@link CourseSession} following criteria in the given {@link Filter}.
     *
     * @param courses The courses to filter
     * @param filter The filter to apply. It must be not null.
     * @return The filtered list of courses with the filtered sessions.
     */
    // TODO Test
    public static List<Course> filterCoursesAndSessions(List<Course> courses, Filter filter) {
        // Filter courses
        String keyword = filter.getKeyword();
        if (keyword != null && !keyword.equals(""))
            courses = courses.stream()
                    .filter(course -> Filter.filterCourseTitle(course, keyword))
                    .collect(Collectors.toList());

        // Filter sessions
        LocalDate startingDate = filter.getStartingDate(),
                endingDate = filter.getEndingDate();
        String city = filter.getCity();
        if (startingDate != null || endingDate != null || (city != null && !city.equals("")))
            courses.forEach(course -> course.setSessions(
                    course.getSessions().stream().filter(session -> (
                            (startingDate == null || Filter.filterSessionBeginAfter(session, startingDate)) &&
                                    (endingDate == null || Filter.filterSessionBeginAfter(session, endingDate)) &&
                                    (city == null || city.equals("") || Filter.filterSessionInCity(session, city))
                    )).collect(Collectors.toList())
            ));

        return courses;
    }

    /**
     * Test if a {@link Course} can be kept by testing if its title contains a given keyword.
     *
     * @param course The course to test.
     * @param keyword The keyword to search in the course title. It must be not null and not blank.
     * @return The result of the test:
     * <ul>
     *     <li>true if the course title contains keyword</li>
     *     <li>false if keyword is not in the course title</li>
     * </ul>
     */
    public static boolean filterCourseTitle(Course course, String keyword) {
        return course.getTitle().contains(keyword);
    }

    /**
     * Test if a {@link CourseSession} can be kept by testing if its begin and ending are in the given interval.
     *
     * @param session The session to test.
     * @param startingDate The minimum date to begin the session. It has to be not null.
     * @return The result of the test:
     * <ul>
     *     <li>true if the session begin in or before the given date</li>
     *     <li>false if startingDate is null or if the session begins before it.</li>
     * </ul>
     */
    public static boolean filterSessionBeginAfter(CourseSession session, LocalDate startingDate) {
        return session.getStartingDate().isEqual(startingDate) || session.getStartingDate().isAfter(startingDate);
    }

    /**
     * Test if a {@link CourseSession} can be kept by testing if its begin and ending are in the given interval.
     *
     * @param session The session to test.
     * @param endingDate The maximum date to finish the session. It has to be not null.
     * @return The result of the test:
     * <ul>
     *     <li>true if the session finish in or after the given date</li>
     *     <li>false if endingDate is null or if the session finish after it.</li>
     * </ul>
     */
    public static boolean filterSessionFinishBefore(CourseSession session, LocalDate endingDate) {
        return session.getEndingDate().isEqual(endingDate) || session.getEndingDate().isBefore(endingDate);
    }

    /**
     * Test if a {@link CourseSession} can be kept by testing if it takes place in the given city.
     *
     * @param session The session to test.
     * @param city The city where the session has to take place. It must be not null and not blank.
     * @return The result of the test:
     * <ul>
     *     <li>true if the takes place in the given city.</li>
     *     <li>false if the session does not takes place in the given city</li>
     * </ul>
     */
    public static boolean filterSessionInCity(CourseSession session, String city) {
        return session.getLocation().getCity().equals(city);
    }
}
