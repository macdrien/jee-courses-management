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
            courses = Filter.filterCoursesAndSessions(courses, filter);
        }

        return courses;
    }

}
