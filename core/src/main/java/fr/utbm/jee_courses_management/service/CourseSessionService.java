package fr.utbm.jee_courses_management.service;

import fr.utbm.jee_courses_management.entity.CourseSession;

import java.io.Serializable;

/** Service which run operations on or about {@link CourseSession}. */
public interface CourseSessionService extends Serializable {

    /**
     * Get a {@link CourseSession} identified by its id.
     *
     * @param id The id of the searched {@link CourseSession}.
     * @return The found {@link CourseSession}.<br/>
     *          Null if the {@link fr.utbm.jee_courses_management.entity.CourseSession} does not exist.
     */
    CourseSession getCourseSession(Integer id);
}
