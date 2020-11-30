package fr.utbm.jee_courses_management.controller;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.service.CourseService;
import fr.utbm.jee_courses_management.service.implementation.CourseServiceImpl;
import fr.utbm.jee_courses_management.util.Filter;

import java.io.Serializable;
import java.util.List;

public class CourseController implements Serializable {

    private final CourseService service;

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
    public List<Course> getCourses(Filter filter) {
        return service.getCourses(filter);
    }
}
