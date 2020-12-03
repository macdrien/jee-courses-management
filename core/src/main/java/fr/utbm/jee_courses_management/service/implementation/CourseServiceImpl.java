package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.repository.CourseRepository;
import fr.utbm.jee_courses_management.repository.CourseSessionRepository;
import fr.utbm.jee_courses_management.service.CourseService;
import fr.utbm.jee_courses_management.util.Filter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    private final CourseSessionRepository sessionRepository;

    public CourseServiceImpl() {
        repository = new CourseRepository();
        sessionRepository = new CourseSessionRepository();
    }

    /** @see fr.utbm.jee_courses_management.service.CourseService#getCourses(Filter) */
    // TODO Test
    @Override
    public List<Course> getCourses(Filter filter) {
        List<Course> courses = repository.getCourses(true);

        if (filter != null) {
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
        }

        return courses;
    }

}
