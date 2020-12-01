package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.repository.CourseRepository;
import fr.utbm.jee_courses_management.repository.CourseSessionRepository;
import fr.utbm.jee_courses_management.service.CourseService;
import fr.utbm.jee_courses_management.util.Filter;

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
    @Override
    public List<Course> getCourses(Filter filter) {
        List<Course> courses = repository.getCourses(true);

        if (filter != null) {
            courses = courses.stream()
                    .filter(course -> filter.getKeyword() == null || course.getTitle().contains(filter.getKeyword()))
                    .collect(Collectors.toList());
            courses.forEach(course -> course.setSessions(
                    course.getSessions().stream().filter(session -> (
                    (filter.getStartingDate() == null ||
                        session.getStartingDate().isEqual(filter.getStartingDate()) ||
                        session.getStartingDate().isAfter(filter.getStartingDate())) &&
                        (filter.getEndingDate() == null ||
                            session.getEndingDate().isEqual(filter.getEndingDate()) ||
                            session.getEndingDate().isAfter(filter.getEndingDate())) &&
                        (filter.getCity() == null || session.getLocation().getCity().equals(filter.getCity()))
            )).collect(Collectors.toList())));
        }

        return courses;
    }

}
