package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.Client;
import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.repository.CourseRepository;
import fr.utbm.jee_courses_management.repository.CourseSessionRepository;
import fr.utbm.jee_courses_management.service.CourseService;
import fr.utbm.jee_courses_management.util.Filter;
import lombok.AllArgsConstructor;

import java.util.List;

/** An implementation of the service {@link Course}. */
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    /** A repository a access to the {@link Client} entity. */
    private final CourseRepository repository;

    /** Repository for operations on {@link fr.utbm.jee_courses_management.entity.CourseSession} */
    // TODO Transform using {@link CourseSessionService} to respect SOA layers.
    private final CourseSessionRepository sessionRepository;

    /** (constructor)
     * Default constructor to initialize both of the private and final class fields.
     */
    public CourseServiceImpl() {
        repository = new CourseRepository();
        sessionRepository = new CourseSessionRepository();
    }

    /** @see fr.utbm.jee_courses_management.service.CourseService#getCourses(Filter) */
    @Override
    public List<Course> getCourses(Filter filter) {
        List<Course> courses = repository.getCourses(true);
        return filter != null ? Filter.filterCoursesAndSessions(courses, filter) : courses;
    }

}
