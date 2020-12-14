package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.repository.CourseSessionRepository;
import fr.utbm.jee_courses_management.service.CourseSessionService;

/** @see CourseSessionService */
public class CourseSessionServiceImpl implements CourseSessionService {

    /** Repository for operations on the {@link CourseSessionRepository} entity. */
    private CourseSessionRepository repository;

    /** (constructor)
     * Default constructor which initialize the repository.
     */
    public CourseSessionServiceImpl() {
        repository = new CourseSessionRepository();
    }

    /** @see fr.utbm.jee_courses_management.service.CourseSessionService#getCourseSession(Integer)  */
    @Override
    public CourseSession getCourseSession(Integer id) {
        return repository.getCourseSessionById(id);
    }
}
