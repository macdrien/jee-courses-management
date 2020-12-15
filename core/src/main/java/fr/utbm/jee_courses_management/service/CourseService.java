package fr.utbm.jee_courses_management.service;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.util.Filter;

import java.io.Serializable;
import java.util.List;

/** Service which run operations on or about {@link Course}. */
public interface CourseService extends Serializable {

    /**
     * Get all courses which match with the given filter (if there is a filter).
     * If there is no filter then return all courses.
     *
     * @param filter An object which describes criteria for returned courses
     * @return A {@link List} of {@link Course} which match with the filter.
     */
    List<Course> getCourses(Filter filter);
}
