package fr.utbm.jee_courses_management.controller;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.service.CourseService;
import fr.utbm.jee_courses_management.service.implementation.CourseServiceImpl;
import fr.utbm.jee_courses_management.util.Filter;

import java.io.Serializable;
import java.util.List;

/**
 * Controller to publish operations on {@link Course}
 */
public class CourseController implements Serializable {

    /** Service to run operations on {@link Course} */
    private final CourseService service;

    /** (constructor)
     * Default constructor to initialize {@link CourseService}
     */
    public CourseController() {
        service = new CourseServiceImpl();
    }

    /**
     * Get all courses which match with the given filter (if there is a filter).
     * If there is no filter then return all courses.
     *
     * @param filter An object which describes criteria for returned courses
     * @return A {@link List} of {@link Course} which match with the filter.
     */
    // TODO Test
    public List<Course> getCourses(Filter filter) {
        return service.getCourses(filter);
    }
}
