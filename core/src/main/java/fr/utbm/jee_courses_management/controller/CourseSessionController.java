package fr.utbm.jee_courses_management.controller;

import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.service.CourseSessionService;
import fr.utbm.jee_courses_management.service.implementation.CourseSessionServiceImpl;
import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * Controller to publish operations on {@link CourseSession}
 */
@AllArgsConstructor
public class CourseSessionController implements Serializable {

    /** Service to run operations on {@link CourseSession} */
    private final CourseSessionService service;

    /** (constructor)
     * Default constructor to initialize {@link CourseSessionService}
     */
    public CourseSessionController() {
        service = new CourseSessionServiceImpl();
    }

    /**
     * Get a {@link CourseSession} identified by the given id.
     *
     * @param id The identifier of the searched {@link CourseSession}
     * @return The found {@link CourseSession}. Null if it does not exist.
     */
    public CourseSession getCourseSessionById(Integer id) {
        return service.getCourseSession(id);
    }
}
